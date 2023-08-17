
## Installation Guide

1. Install RabbitMq on docker by this command
```bash
docker run -d --hostname my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```

1. Check MQ is running
```bash
docker ps
```