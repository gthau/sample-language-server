package org.example.lsp;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class SampleLspLauncher {
//    private static Logger logger = LogManager.getLogger(SampleLspLauncher.class);
    private static Logger logger = LoggerFactory.getLogger(SampleLspLauncher.class);

    public static void main(String[] args)
            throws InterruptedException, ExecutionException {
        logger.debug("Launching Sample Language Server now");
        startServer(System.in, System.out);
    }

    private static void startServer(InputStream in, OutputStream out)
            throws InterruptedException, ExecutionException {
        SampleLanguageServer server = new SampleLanguageServer();
        Launcher<LanguageClient> launcher = Launcher.createLauncher(server, LanguageClient.class, in, out);
        LanguageClient client = launcher.getRemoteProxy();
        server.connect(client);
        Future<?> startListening = launcher.startListening();
        startListening.get();
        logger.debug("started listening to client");
    }
}