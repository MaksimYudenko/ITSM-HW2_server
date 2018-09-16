package by.itsm.course.hw2s.core;

import by.itsm.course.hw2s.core.model.Request;
import by.itsm.course.hw2s.core.model.Response;
import by.itsm.course.hw2s.core.proccessors.RequestProcessor;
import by.itsm.course.hw2s.util.postProcessing.Metamorphosis;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private final int port;
    private final int threadCount;
    private final ObjectMapper mapper;
    private final Provider<List<RequestProcessor>> requestProcessorProvider;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    public Server(int port, int threadCount, ObjectMapper mapper
            , Provider<List<RequestProcessor>> requestProcessorProvider) {
        this.port = port;
        this.threadCount = threadCount;
        this.mapper = mapper;
        this.requestProcessorProvider = requestProcessorProvider;
    }

    @PostConstruct
    public void init() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }

    @Metamorphosis
    private void connect(Socket socket) {
        executorService.execute(() -> {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                DataInputStream dis = new DataInputStream(is);
                DataOutputStream dos = new DataOutputStream(os);
                String requestString = dis.readUTF();
                Request request = mapper.readValue(requestString, Request.class);
                Response response = null;

                List<RequestProcessor> processors = requestProcessorProvider.get();
                for (RequestProcessor processor : processors) {
                    if (processor.isAdmissible(request)) {
                        response = new Response(processor.process(request));
                        break;
                    }
                }
                String responseString = mapper.writeValueAsString(response);
                dos.writeUTF(responseString);
                dos.flush();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            connect(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}