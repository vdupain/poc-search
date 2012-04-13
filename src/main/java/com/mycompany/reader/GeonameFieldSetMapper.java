package com.mycompany.reader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mycompany.Geoname;

public class GeonameFieldSetMapper implements FieldSetMapper<Geoname> {

	public Geoname mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null)
			return null;
		Geoname geoname = new Geoname();
		geoname.setGeonameId(fieldSet.readInt(0));
		geoname.setName(fieldSet.readString(1));
		geoname.setAsciiname(fieldSet.readString(2));
		geoname.setAlternatenames(fieldSet.readString(3));
		geoname.setLatitude(fieldSet.readDouble(4));
		geoname.setLongitude(fieldSet.readDouble(5));
		geoname.setFeatureClass(fieldSet.readString(6));
		geoname.setFeatureCode(fieldSet.readString(7));
		geoname.setCountryCode(fieldSet.readString(8));
		geoname.setCC2(fieldSet.readString(9));
		geoname.setAdmin1Code(fieldSet.readString(10));
		geoname.setAdmin2Code(fieldSet.readString(11));
		geoname.setAdmin3Code(fieldSet.readString(12));
		geoname.setAdmin4Code(fieldSet.readString(13));
		geoname.setPopulation(fieldSet.readInt(14));
		geoname.setElevation(fieldSet.readInt(15, 0));
		geoname.setDem(fieldSet.readInt(16));
		geoname.setTimezone(fieldSet.readString(17));
		geoname.setModificationDate(fieldSet.readDate(18));
		return geoname;
	}

}
