package it.emanuelebondattidev.WebfluxLearning.utils;

import it.emanuelebondattidev.WebfluxLearning.utils.functional.UnsafeSupplier;

//@Slf4j
public class Safe {

	// makes sense? we'll see in the next episodes
	public static <T> T invoke(UnsafeSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
//            log.error( "Error during .invoke" );
        	System.out.println( "Error during .invoke -> %s".formatted( e.getMessage() ) );
            return null;
        }
    }




}
