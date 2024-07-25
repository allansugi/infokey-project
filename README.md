# infokey-project

This is a my first personal project building a full stack web application
using spring boot and react.

## Requirement
- spring boot
- docker
- java version 17


## Run the application

currently in dev mode, so to run the application

### Backend
go to `/backend` directory. If you just clone the repo, you need to run:
```bash
chmod +x keygen.sh
./keygen.sh
```
the command above will generate RSA public and private key. Then to run the backend server, run:
```bash
mvn install
docker-compose up --build
```
the server will be run on localhost port 8080
### Frontend
go to client directory and run:

```bash
npm install
npm run dev
```
the frontend will be run on localhost port 5173