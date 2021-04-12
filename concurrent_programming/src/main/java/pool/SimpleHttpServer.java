package pool;

import pool.impl.ThreadPool;

import java.net.Socket;
import java.net.http.HttpRequest;

public class SimpleHttpServer {

//    static ThreadPool<HttpRequestHandler>


    static class HttpRequestHandler implements Runnable{

        private Socket socket;

        public HttpRequestHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }

}
