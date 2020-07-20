package org.example.lsp;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

class SampleTextDocumentService implements TextDocumentService {
//    private static Logger logger = LogManager.getLogger(SampleTextDocumentService.class);
private static Logger logger = LoggerFactory.getLogger(SampleTextDocumentService.class);

    @Override
    public CompletableFuture<Hover> hover(HoverParams params) {
        logger.debug("in SampleTextDocumentService::hover");

        Hover res = new Hover();
        MarkupContent markupContent = new MarkupContent(MarkupKind.PLAINTEXT, "This is a hover test.");
        res.setContents(markupContent);

        return CompletableFuture.supplyAsync(() -> res);
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        logger.debug("in SampleTextDocumentService::didOpen, not yet implemented");
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        logger.debug("in SampleTextDocumentService::didChange, not yet implemented");
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        logger.debug("in SampleTextDocumentService::didClose, not yet implemented");
    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        logger.debug("in SampleTextDocumentService::didSave, not yet implemented");
    }
}
