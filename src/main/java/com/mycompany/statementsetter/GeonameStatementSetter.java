package com.mycompany.statementsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.mycompany.Geoname;

@Component
public class GeonameStatementSetter implements
		ItemPreparedStatementSetter<Geoname> {
	public void setValues(Geoname geoname, PreparedStatement ps)
			throws SQLException {
		ps.setInt(1, geoname.getGeonameId());
		ps.setString(2, geoname.getName());
		ps.setString(3, geoname.getAsciiname());
		ps.setString(4, geoname.getAlternatenames());
		ps.setDouble(5, geoname.getLatitude());
		ps.setDouble(6, geoname.getLongitude());
		ps.setString(7, geoname.getFeatureClass());
		ps.setString(8, geoname.getFeatureCode());
		ps.setString(9, geoname.getCountryCode());
		ps.setString(10, geoname.getCc2());
		ps.setString(11, geoname.getAdmin1Code());
		ps.setString(12, geoname.getAdmin2Code());
		ps.setString(13, geoname.getAdmin3Code());
		ps.setString(14, geoname.getAdmin4Code());
		ps.setInt(15, geoname.getPopulation());
		ps.setInt(16, geoname.getElevation());
		ps.setInt(17, geoname.getDem());
		ps.setString(18, geoname.getTimezone());
		ps.setDate(19, new java.sql.Date(geoname.getModificationDate()
				.getTime()));
	}
}
