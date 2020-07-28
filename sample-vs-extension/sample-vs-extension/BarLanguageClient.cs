using Microsoft.VisualStudio.LanguageServer.Client;
using Microsoft.VisualStudio.Shell;
using Microsoft.VisualStudio.Threading;
using Microsoft.VisualStudio.Utilities;
using System;
using System.Collections.Generic;
using System.ComponentModel.Composition;
using System.Diagnostics;
using System.Threading;
using System.Threading.Tasks;
using Task = System.Threading.Tasks.Task;
using System.IO;

namespace SampleLanguageExtension
{
    [ContentType("bar")]
    [Export(typeof(ILanguageClient))]
    public class BarLanguageClient : ILanguageClient
    {
        public string Name => "Bar Language Extension";

        public IEnumerable<string> ConfigurationSections => null;

        public object InitializationOptions => null;

        public IEnumerable<string> FilesToWatch => null;

        public event AsyncEventHandler<EventArgs> StartAsync;
        public event AsyncEventHandler<EventArgs> StopAsync;

        public async Task<Connection> ActivateAsync(CancellationToken token)
        {
            await Task.Yield();

            //string workingDir = Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
            string jar = Path.Combine("language-server", "lsp-server-1.0-SNAPSHOT-all.jar");
            string jarNoLogging = Path.Combine("language-server", "lsp-server-1.0-SNAPSHOT-all-nologging.jar");
            string jarLog4j = Path.Combine("language-server", "lsp-server-1.0-SNAPSHOT-all-log4j.jar");
            string jarLogback = Path.Combine("language-server", "lsp-server-1.0-SNAPSHOT-all-logback.jar");
            
            ProcessStartInfo info = new ProcessStartInfo();
            info.FileName = "java.exe";
            info.Arguments = "-jar " + jar;
            //info.WorkingDirectory = workingDir;
            info.RedirectStandardInput = true;
            info.RedirectStandardOutput = true;
            info.UseShellExecute = false;
            info.CreateNoWindow = true;

            Process process = new Process();
            process.StartInfo = info;

            if (process.Start())
            {
                return new Connection(process.StandardOutput.BaseStream, process.StandardInput.BaseStream);
            }

            return null;
        }

        public async Task OnLoadedAsync()
        {
            await StartAsync.InvokeAsync(this, EventArgs.Empty);
        }

        public Task OnServerInitializeFailedAsync(Exception e)
        {
            return Task.CompletedTask;
        }

        public Task OnServerInitializedAsync()
        {
            return Task.CompletedTask;
        }
    }
}
