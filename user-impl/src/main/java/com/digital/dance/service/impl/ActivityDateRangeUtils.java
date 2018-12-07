/**
 * 
 */
package com.digital.dance.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.digital.dance.user.commons.Log;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author Xinyu Liu
 * @date 2016年5月16日 下午8:26:29
 */
public class ActivityDateRangeUtils {

	private static final Log logger = new Log( ActivityDateRangeUtils.class );
	private static SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

	public static String dateRangeString;

	public static Date startDate;

	public static Date endDate;
	
	public static boolean inrangeDate( Date date ) {
		return ActivityDateRangeUtils.startDate.before( date ) && ActivityDateRangeUtils.endDate.after( date );
	}

	public void setDateRangeString( String dateRangeString ) {
		ActivityDateRangeUtils.dateRangeString = dateRangeString;
		logger.error( "init activity setting: set date range ." + ActivityDateRangeUtils.dateRangeString );
		try {
			if( StringUtils.isNotEmpty( ActivityDateRangeUtils.dateRangeString ) ) {
				String[] dateRangeStrings = ActivityDateRangeUtils.dateRangeString.split( "," );
				setStartDate( sdf.parse( dateRangeStrings[ 0 ] ) );
				setEndDate( sdf.parse( dateRangeStrings[ 1 ] ) );
			}
		} catch( ParseException e ) {
			logger.error( "pull order: set date range error." + dateRangeString, e );
		}
	}

	public void setStartDate( Date startDate ) {
		ActivityDateRangeUtils.startDate = startDate;
	}

	public void setEndDate( Date endDate ) {
		ActivityDateRangeUtils.endDate = endDate;
	}

}
