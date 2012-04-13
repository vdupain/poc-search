package com.mycompany.reader;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mycompany.Country;

public class CountryFieldSetMapper implements FieldSetMapper<Country> {

	public Country mapFieldSet(FieldSet fieldSet) throws BindException {
		if (fieldSet == null)
			return null;
		Country country = new Country();
		country.setGeonameId(fieldSet.readInt(0));
		country.setName(fieldSet.readString(1));
		country.setAsciiname(fieldSet.readString(2));
		country.setAlternatenames(fieldSet.readString(3));
		country.setLatitude(fieldSet.readDouble(4));
		country.setLongitude(fieldSet.readDouble(5));
		country.setFeatureClass(fieldSet.readString(6));
		country.setFeatureCode(fieldSet.readString(7));
		country.setCountryCode(fieldSet.readString(8));
		country.setCC2(fieldSet.readString(9));
		country.setAdmin1Code(fieldSet.readString(10));
		country.setAdmin2Code(fieldSet.readString(11));
		country.setAdmin3Code(fieldSet.readString(12));
		country.setAdmin4Code(fieldSet.readString(13));
		country.setPopulation(fieldSet.readInt(14));
		country.setElevation(fieldSet.readInt(15, 0));
		country.setDem(fieldSet.readInt(16));
		country.setTimezone(fieldSet.readString(17));
		country.setModificationDate(fieldSet.readDate(18));
		return country;
	}

}
