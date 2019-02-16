package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.*;


public class FactoryXML implements ReportFactory{

	@Override
	public ReportBody body() {

		return new XMLReportBody();
	}

	@Override
	public ReportFooter footer() {

		return new XMLReportFooter();
	}

	@Override
	public ReportHeader header() {

		return new XMLReportHeader();
	}

}