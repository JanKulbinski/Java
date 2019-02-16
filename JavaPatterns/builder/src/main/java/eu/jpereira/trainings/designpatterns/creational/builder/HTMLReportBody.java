package eu.jpereira.trainings.designpatterns.creational.builder;

import java.util.Iterator;


import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

public class HTMLReportBody implements ReportBody {
	
	private class Build {
		
		private StringBuffer delegate;
		
		public Build() {
			delegate = new StringBuffer();
		}
		public Build customerInfo() {
			delegate.append("<span class=\"customerName\">");
			delegate.append(saleEntry.getCustomer().getName());
			delegate.append("</span><span class=\"customerPhone\">");
			delegate.append(saleEntry.getCustomer().getPhone());
			delegate.append("</span>");
			delegate.append("<items>");
			return this;
		}
		
		public Build arrayOfItems() {
			Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
			while ( it.hasNext() ) {
				
				SoldItem soldEntry= it.next();
				delegate.append("<item><name>");
				delegate.append(soldEntry.getName());
				delegate.append("</name><quantity>");
				delegate.append(soldEntry.getQuantity());
				delegate.append("</quantity><price>");
				delegate.append(soldEntry.getUnitPrice());
				delegate.append("</price></item>");
			}
			delegate.append("</items>");
			
			return this;
		}
		
		public StringBuffer build() {
			return this.delegate;
		}
	}
	private StringBuffer delegate;
	private SaleEntry saleEntry;

	/**
	 * @param reportDate
	 */
	public HTMLReportBody(SaleEntry saleEntry) {
		this.saleEntry = saleEntry;
		delegate = new StringBuffer();
		
		delegate = new Build()
				 .customerInfo()
				 .arrayOfItems()
				 .build();
	}
	@Override
	public Object getAsString() {
		return this.delegate.toString();
	}

	public void putContent(Object content) {
		this.delegate.append(content);
		
	}

}
