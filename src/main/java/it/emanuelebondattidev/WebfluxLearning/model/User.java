package it.emanuelebondattidev.WebfluxLearning.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1557757378440383762L;
	
	private String firstName;
	private String lastName;
}
