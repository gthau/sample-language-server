package org.example.lsp;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

class SampleLanguageServer implements LanguageServer, LanguageClientAware {
//    private static Logger logger = LogManager.getLogger(SampleLanguageServer.class);
    private static Logger logger = LoggerFactory.getLogger(SampleLanguageServer.class);

    private LanguageClient client;
    private SampleTextDocumentService textDocumentService = new SampleTextDocumentService();
    private SampleWorkspaceService workspaceService = new SampleWorkspaceService();

    @Override
    public void connect(LanguageClient client) {
        this.client = client;
    }

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        logger.debug("in SampleLanguageServer::initialize");
        logger.debug(params.toString());

        InitializeResult res = new InitializeResult(new ServerCapabilities());

        res.getCapabilities().setTextDocumentSync(TextDocumentSyncKind.Full);
        res.getCapabilities().setHoverProvider(true);
        res.getCapabilities().setDefinitionProvider(false);
        res.getCapabilities().setDocumentLinkProvider(new DocumentLinkOptions(false));
        res.getCapabilities().setDocumentSymbolProvider(false);
        res.getCapabilities().setReferencesProvider(false);
        res.getCapabilities().setWorkspaceSymbolProvider(false);

        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        logger.debug("in SampleLanguageServer::shutdown");
        return CompletableFuture.supplyAsync(() -> new Object());
    }

    @Override
    public void exit() {
        logger.debug("in SampleLanguageServer::exit");
        System.exit(0);
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        return this.textDocumentService;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return this.workspaceService;
    }
}