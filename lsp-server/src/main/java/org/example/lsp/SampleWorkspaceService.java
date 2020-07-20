package org.example.lsp;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.eclipse.lsp4j.DidChangeConfigurationParams;
import org.eclipse.lsp4j.DidChangeWatchedFilesParams;
import org.eclipse.lsp4j.services.WorkspaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SampleWorkspaceService implements WorkspaceService {
//    private static Logger logger = LogManager.getLogger(SampleWorkspaceService.class);
private static Logger logger = LoggerFactory.getLogger(SampleWorkspaceService.class);

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        logger.debug("in SampleWorkspaceService::didChangeConfiguration");
    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
        logger.debug("in SampleWorkspaceService::didChangeWatchedFiles");
    }
}