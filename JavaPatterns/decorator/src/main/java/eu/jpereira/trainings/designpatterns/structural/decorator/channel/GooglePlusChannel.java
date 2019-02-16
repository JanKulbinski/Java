package eu.jpereira.trainings.designpatterns.structural.decorator.channel;

public class GooglePlusChannel implements SocialChannel {
	public static final String NAME = "GooglePlus";

	/* (non-Javadoc)
	 * @see eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel#deliverMessage(java.lang.String)
	 */
	@Override
	public void deliverMessage(String string) {
		//What to do??
		System.out.println("GooglePlus: "+string);

	}
}
