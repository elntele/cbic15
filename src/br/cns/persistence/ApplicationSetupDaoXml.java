package br.cns.persistence;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import br.cns.experiments.setup.ApplicationSetup;

public class ApplicationSetupDaoXml implements IApplicationSetupDao {
	private static final ApplicationSetupDaoXml instance = new ApplicationSetupDaoXml();

	private ApplicationSetupDaoXml() {
	}

	@Override
	public void save(String xml, ApplicationSetup object) throws IOException {
		try {
			JaxbUtils.getInstance().save(xml, object);
		} catch (JAXBException e) {
			throw new IOException(e);
		}
	}

	@Override
	public ApplicationSetup load(String xml) throws IOException {
		ApplicationSetup setup = null;
		try {
			setup = (ApplicationSetup) JaxbUtils.getInstance().load(xml,
					ApplicationSetup.class);
		} catch (JAXBException e) {
			throw new IOException(e);
		}
		return setup;
	}

	public static ApplicationSetupDaoXml getInstance() {
		return instance;
	}

}
