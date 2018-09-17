### HW2 server
- to build a docker image:
```
docker build -t hw2s .
```
- to run the application:
```
docker run -ti --network host --name hw2-spring-server hw2s
```
