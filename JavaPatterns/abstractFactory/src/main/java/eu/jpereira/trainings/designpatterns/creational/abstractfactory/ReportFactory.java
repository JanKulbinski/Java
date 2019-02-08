package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

public interface ReportFactory {
	public  ReportBody body();
	public ReportFooter footer();
	public ReportHeader header();
	
}
