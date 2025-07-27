package it.emanuelebondattidev.WebfluxLearning.utils;

import it.emanuelebondattidev.WebfluxLearning.utils.functional.UnsafeSupplier;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class Safe {

	// makes sense? we'll see in the next episodes
	public static <T> T invoke(UnsafeSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
//            log.error( "Error during .invoke" );
        	System.out.println( "Error during .invoke" );
            return null;
        }
    }




}
