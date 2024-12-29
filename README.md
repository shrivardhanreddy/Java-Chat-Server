# Java-Chat-Server
Containerize the Server:
```docker build -t <servername> .```
```docker run -d <servername>```
```docker logs <log-id>```

Containerize the Client:
```docker build -t <clientname> .```
```docker run -d clientname```
```docker logs <log-id>```

Pushing Docker Images to a HUB
```docker login```
```docker push </server-path>```
```docker push </client-path>```
