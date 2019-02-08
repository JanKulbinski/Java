package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator {

	private String pattern;

	/**
	 * @param url
	 */
	public WordCensor(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @param string
	 * @param channel
	 */
	public WordCensor(String pattern, SocialChannel channel) {
		this.pattern = pattern;
		this.delegate = channel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jpereira.trainings.designpatterns.structural.decorator.channel.
	 * SocialChannel#deliverMessage(java.lang.String)
	 */
	@Override
	public void deliverMessage(String message) {
		
		StringBuilder builder = new StringBuilder(); 
		List<String> list = Arrays.asList(message.split(" "));
		for( int i = 0; i < list.size()-1; i++ ) {
			if( list.get(i).equals(pattern) ) {
				list.set(i, "###");
			}
			builder.append(list.get(i));
			builder.append(" ");
		}
		
		if( list.get(list.size()-1).equals(pattern) ) {
			list.set(list.size()-1, "###");
		}
		builder.append(list.get(list.size()-1));
		
		if (delegate != null) {
			delegate.deliverMessage(builder.toString());
		}

	}


}
