package com.mycompany.statementsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.mycompany.Country;

@Component
public class CountryStatementSetter implements
		ItemPreparedStatementSetter<Country> {
	public void setValues(Country country, PreparedStatement ps)
			throws SQLException {
		ps.setInt(1, country.getGeonameId());
		ps.setString(2, country.getName());
		ps.setString(3, country.getAsciiname());
		ps.setString(4, country.getAlternatenames());
		ps.setDouble(5, country.getLatitude());
		ps.setDouble(6, country.getLongitude());
		ps.setString(7, country.getFeatureClass());
		ps.setString(8, country.getFeatureCode());
		ps.setString(9, country.getCountryCode());
		ps.setString(10, country.getCc2());
		ps.setString(11, country.getAdmin1Code());
		ps.setString(12, country.getAdmin2Code());
		ps.setString(13, country.getAdmin3Code());
		ps.setString(14, country.getAdmin4Code());
		ps.setInt(15, country.getPopulation());
		ps.setInt(16, country.getElevation());
		ps.setInt(17, country.getDem());
		ps.setString(18, country.getTimezone());
		ps.setDate(19, new java.sql.Date(country.getModificationDate()
				.getTime()));
	}
}
