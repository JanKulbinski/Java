package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.*;


public class FactoryJSON implements ReportFactory{

	@Override
	public ReportBody body() {

		return new JSONReportBody();
	}

	@Override
	public ReportFooter footer() {

		return new JSONReportFooter();
	}

	@Override
	public ReportHeader header() {

		return new JSONReportHeader();
	}

}
