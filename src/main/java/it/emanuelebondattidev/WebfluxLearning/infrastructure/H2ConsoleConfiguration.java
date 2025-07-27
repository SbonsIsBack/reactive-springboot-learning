package it.emanuelebondattidev.WebfluxLearning.infrastructure;

import org.h2.tools.Server;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import it.emanuelebondattidev.WebfluxLearning.utils.Safe;

@Configuration
//@Profile({"test","dev"}) //linked to spring.profiles - enables/disables config based on that
//alternatively is possible to specify one or more conditions based on spring.profiles. This one disables for prod or production
@Profile("!prod & !production") 
public class H2ConsoleConfiguration {
	
	private Server webServer;
	
	@EventListener( ApplicationStartedEvent.class )
	public void start() {
		
		final String WEB_PORT = "8095";
		Safe.invoke( 
				() -> { 
					this.webServer = Server.createWebServer( "-webPort", WEB_PORT ).start();
					return this.webServer;
					} 
				);
		
	}
	
	@EventListener( ContextClosedEvent.class )
	public void stop() {
		
		if( this.webServer != null && this.webServer.isRunning(false) )
			this.webServer.stop();
		
	}
	
	
	
	
}
