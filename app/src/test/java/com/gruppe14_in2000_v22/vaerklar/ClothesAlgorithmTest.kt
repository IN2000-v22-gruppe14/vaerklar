package com.gruppe14_in2000_v22.vaerklar

import com.gruppe14_in2000_v22.vaerklar.data.ClothesAlgorithm
import com.gruppe14_in2000_v22.vaerklar.data.WeatherData
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ClothesAlgorithmTest {
    private val exampleResponseRaw = """
        {
        	"type": "Feature",
        	"geometry": {
        		"type": "Point",
        		"coordinates": [
        			10,
        			59,
        			63
        		]
        	},
        	"properties": {
        		"meta": {
        			"updated_at": "2022-05-18T20:36:05Z",
        			"units": {
        				"air_pressure_at_sea_level": "hPa",
        				"air_temperature": "celsius",
        				"air_temperature_max": "celsius",
        				"air_temperature_min": "celsius",
        				"air_temperature_percentile_10": "celsius",
        				"air_temperature_percentile_90": "celsius",
        				"cloud_area_fraction": "%",
        				"cloud_area_fraction_high": "%",
        				"cloud_area_fraction_low": "%",
        				"cloud_area_fraction_medium": "%",
        				"dew_point_temperature": "celsius",
        				"fog_area_fraction": "%",
        				"precipitation_amount": "mm",
        				"precipitation_amount_max": "mm",
        				"precipitation_amount_min": "mm",
        				"probability_of_precipitation": "%",
        				"probability_of_thunder": "%",
        				"relative_humidity": "%",
        				"ultraviolet_index_clear_sky": "1",
        				"wind_from_direction": "degrees",
        				"wind_speed": "m/s",
        				"wind_speed_of_gust": "m/s",
        				"wind_speed_percentile_10": "m/s",
        				"wind_speed_percentile_90": "m/s"
        			}
        		},
        		"timeseries": [
        			{
        				"time": "2022-05-18T21:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1020.1,
        							"air_temperature": 12.5,
        							"air_temperature_percentile_10": 11.4,
        							"air_temperature_percentile_90": 13.1,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.6,
        							"cloud_area_fraction_low": 97.1,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.3,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 86.3,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 49.7,
        							"wind_speed": 1.0,
        							"wind_speed_of_gust": 2.2,
        							"wind_speed_percentile_10": 0.6,
        							"wind_speed_percentile_90": 1.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.3,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 12.3,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-18T22:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1019.8,
        							"air_temperature": 12.3,
        							"air_temperature_percentile_10": 10.4,
        							"air_temperature_percentile_90": 12.9,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.7,
        							"cloud_area_fraction_low": 93.3,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 87.0,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 48.7,
        							"wind_speed": 1.7,
        							"wind_speed_of_gust": 2.9,
        							"wind_speed_percentile_10": 0.5,
        							"wind_speed_percentile_90": 1.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 12.2,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-18T23:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1019.5,
        							"air_temperature": 12.2,
        							"air_temperature_percentile_10": 9.6,
        							"air_temperature_percentile_90": 12.9,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.7,
        							"cloud_area_fraction_low": 84.2,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.4,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 87.9,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 56.9,
        							"wind_speed": 1.7,
        							"wind_speed_of_gust": 3.1,
        							"wind_speed_percentile_10": 0.9,
        							"wind_speed_percentile_90": 1.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 12.1,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1019.0,
        							"air_temperature": 12.1,
        							"air_temperature_percentile_10": 9.0,
        							"air_temperature_percentile_90": 12.7,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.9,
        							"cloud_area_fraction_low": 79.3,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.5,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 88.9,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 37.9,
        							"wind_speed": 1.7,
        							"wind_speed_of_gust": 3.1,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 1.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 4.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.0,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T01:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1018.8,
        							"air_temperature": 11.9,
        							"air_temperature_percentile_10": 8.9,
        							"air_temperature_percentile_90": 12.6,
        							"cloud_area_fraction": 99.9,
        							"cloud_area_fraction_high": 99.8,
        							"cloud_area_fraction_low": 62.1,
        							"cloud_area_fraction_medium": 0.5,
        							"dew_point_temperature": 9.6,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 89.8,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 53.5,
        							"wind_speed": 1.6,
        							"wind_speed_of_gust": 3.1,
        							"wind_speed_percentile_10": 1.2,
        							"wind_speed_percentile_90": 2.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 15.5
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.4
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.6,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T02:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1018.4,
        							"air_temperature": 11.4,
        							"air_temperature_percentile_10": 9.3,
        							"air_temperature_percentile_90": 12.6,
        							"cloud_area_fraction": 97.6,
        							"cloud_area_fraction_high": 95.4,
        							"cloud_area_fraction_low": 49.2,
        							"cloud_area_fraction_medium": 2.4,
        							"dew_point_temperature": 9.5,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 91.9,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 41.8,
        							"wind_speed": 2.0,
        							"wind_speed_of_gust": 3.5,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 2.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 17.2
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.4,
        							"probability_of_thunder": 0.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.4,
        							"air_temperature_min": 11.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T03:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1018.0,
        							"air_temperature": 11.0,
        							"air_temperature_percentile_10": 9.3,
        							"air_temperature_percentile_90": 12.5,
        							"cloud_area_fraction": 98.9,
        							"cloud_area_fraction_high": 98.2,
        							"cloud_area_fraction_low": 40.0,
        							"cloud_area_fraction_medium": 10.0,
        							"dew_point_temperature": 9.8,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 95.4,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 43.9,
        							"wind_speed": 2.8,
        							"wind_speed_of_gust": 5.1,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 19.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.7
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 15.3,
        							"air_temperature_min": 11.3,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T04:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.6,
        							"air_temperature": 11.3,
        							"air_temperature_percentile_10": 9.5,
        							"air_temperature_percentile_90": 12.6,
        							"cloud_area_fraction": 99.8,
        							"cloud_area_fraction_high": 99.5,
        							"cloud_area_fraction_low": 51.0,
        							"cloud_area_fraction_medium": 15.0,
        							"dew_point_temperature": 9.8,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 93.1,
        							"ultraviolet_index_clear_sky": 0.1,
        							"wind_from_direction": 47.4,
        							"wind_speed": 3.2,
        							"wind_speed_of_gust": 5.9,
        							"wind_speed_percentile_10": 1.9,
        							"wind_speed_percentile_90": 3.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 20.5
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.4,
        							"air_temperature_min": 12.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T05:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.4,
        							"air_temperature": 12.0,
        							"air_temperature_percentile_10": 10.1,
        							"air_temperature_percentile_90": 13.0,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 75.6,
        							"cloud_area_fraction_medium": 5.5,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 91.0,
        							"ultraviolet_index_clear_sky": 0.4,
        							"wind_from_direction": 50.6,
        							"wind_speed": 3.4,
        							"wind_speed_of_gust": 6.6,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 3.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 21.3
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 13.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.2,
        							"air_temperature": 13.0,
        							"air_temperature_percentile_10": 10.6,
        							"air_temperature_percentile_90": 13.6,
        							"cloud_area_fraction": 99.9,
        							"cloud_area_fraction_high": 99.1,
        							"cloud_area_fraction_low": 95.0,
        							"cloud_area_fraction_medium": 8.2,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 86.1,
        							"ultraviolet_index_clear_sky": 0.9,
        							"wind_from_direction": 50.7,
        							"wind_speed": 3.3,
        							"wind_speed_of_gust": 6.7,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 3.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 22.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.4
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 13.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 4.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T07:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.2,
        							"air_temperature": 13.6,
        							"air_temperature_percentile_10": 11.1,
        							"air_temperature_percentile_90": 14.3,
        							"cloud_area_fraction": 99.9,
        							"cloud_area_fraction_high": 97.3,
        							"cloud_area_fraction_low": 94.5,
        							"cloud_area_fraction_medium": 27.9,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 82.9,
        							"ultraviolet_index_clear_sky": 1.7,
        							"wind_from_direction": 64.2,
        							"wind_speed": 3.5,
        							"wind_speed_of_gust": 7.0,
        							"wind_speed_percentile_10": 2.4,
        							"wind_speed_percentile_90": 3.9
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 33.2
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 14.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.8,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.4
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T08:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.9,
        							"air_temperature": 14.4,
        							"air_temperature_percentile_10": 11.7,
        							"air_temperature_percentile_90": 15.1,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 93.2,
        							"cloud_area_fraction_low": 73.7,
        							"cloud_area_fraction_medium": 93.8,
        							"dew_point_temperature": 10.0,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 78.9,
        							"ultraviolet_index_clear_sky": 2.8,
        							"wind_from_direction": 69.1,
        							"wind_speed": 3.7,
        							"wind_speed_of_gust": 7.3,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 3.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 42.6
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 15.3,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.1,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 16.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T09:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.9,
        							"air_temperature": 15.3,
        							"air_temperature_percentile_10": 12.0,
        							"air_temperature_percentile_90": 16.0,
        							"cloud_area_fraction": 99.0,
        							"cloud_area_fraction_high": 96.7,
        							"cloud_area_fraction_low": 61.8,
        							"cloud_area_fraction_medium": 32.5,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 75.5,
        							"ultraviolet_index_clear_sky": 3.8,
        							"wind_from_direction": 69.3,
        							"wind_speed": 2.8,
        							"wind_speed_of_gust": 7.5,
        							"wind_speed_percentile_10": 2.3,
        							"wind_speed_percentile_90": 3.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 55.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 14.9,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T10:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.8,
        							"air_temperature": 16.4,
        							"air_temperature_percentile_10": 12.3,
        							"air_temperature_percentile_90": 17.0,
        							"cloud_area_fraction": 98.6,
        							"cloud_area_fraction_high": 96.9,
        							"cloud_area_fraction_low": 28.4,
        							"cloud_area_fraction_medium": 48.2,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 70.6,
        							"ultraviolet_index_clear_sky": 4.6,
        							"wind_from_direction": 84.2,
        							"wind_speed": 2.7,
        							"wind_speed_of_gust": 6.9,
        							"wind_speed_percentile_10": 1.8,
        							"wind_speed_percentile_90": 3.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 69.2
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 3.9,
        							"probability_of_thunder": 0.0
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.5,
        							"air_temperature_min": 13.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T11:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.2,
        							"air_temperature": 16.5,
        							"air_temperature_percentile_10": 13.5,
        							"air_temperature_percentile_90": 17.2,
        							"cloud_area_fraction": 99.9,
        							"cloud_area_fraction_high": 86.2,
        							"cloud_area_fraction_low": 39.7,
        							"cloud_area_fraction_medium": 96.6,
        							"dew_point_temperature": 10.5,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 71.3,
        							"ultraviolet_index_clear_sky": 4.9,
        							"wind_from_direction": 123.0,
        							"wind_speed": 1.2,
        							"wind_speed_of_gust": 6.0,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 2.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 73.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.3,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 20.8,
        							"probability_of_thunder": 0.0
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.0,
        							"air_temperature_min": 12.8,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1017.0,
        							"air_temperature": 15.9,
        							"air_temperature_percentile_10": 13.7,
        							"air_temperature_percentile_90": 16.6,
        							"cloud_area_fraction": 99.8,
        							"cloud_area_fraction_high": 98.6,
        							"cloud_area_fraction_low": 25.5,
        							"cloud_area_fraction_medium": 76.6,
        							"dew_point_temperature": 10.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 75.0,
        							"ultraviolet_index_clear_sky": 4.7,
        							"wind_from_direction": 180.7,
        							"wind_speed": 1.8,
        							"wind_speed_of_gust": 4.5,
        							"wind_speed_percentile_10": 1.2,
        							"wind_speed_percentile_90": 2.0
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 71.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.4,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 21.2,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.0,
        							"air_temperature_min": 12.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.4
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T13:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.1,
        							"air_temperature": 16.0,
        							"air_temperature_percentile_10": 13.4,
        							"air_temperature_percentile_90": 16.6,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 2.0,
        							"cloud_area_fraction_medium": 95.3,
        							"dew_point_temperature": 10.3,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 72.4,
        							"ultraviolet_index_clear_sky": 4.0,
        							"wind_from_direction": 177.6,
        							"wind_speed": 1.4,
        							"wind_speed_of_gust": 4.4,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 2.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 67.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 18.4,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 15.3,
        							"air_temperature_min": 12.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.4,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 23.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T14:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.0,
        							"air_temperature": 15.3,
        							"air_temperature_percentile_10": 12.8,
        							"air_temperature_percentile_90": 16.0,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 8.8,
        							"cloud_area_fraction_medium": 78.9,
        							"dew_point_temperature": 9.9,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 73.2,
        							"ultraviolet_index_clear_sky": 3.1,
        							"wind_from_direction": 210.9,
        							"wind_speed": 2.3,
        							"wind_speed_of_gust": 5.5,
        							"wind_speed_percentile_10": 1.4,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 61.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.2,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.5,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.9,
        							"air_temperature_min": 12.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.6,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 27.4
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T15:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1015.7,
        							"air_temperature": 14.9,
        							"air_temperature_percentile_10": 12.7,
        							"air_temperature_percentile_90": 15.6,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 5.4,
        							"cloud_area_fraction_medium": 85.1,
        							"dew_point_temperature": 9.8,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 74.0,
        							"ultraviolet_index_clear_sky": 2.1,
        							"wind_from_direction": 199.0,
        							"wind_speed": 2.4,
        							"wind_speed_of_gust": 5.0,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 56.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 4.9,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.6,
        							"air_temperature_min": 11.9,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 2.7,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 35.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T16:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1015.8,
        							"air_temperature": 13.6,
        							"air_temperature_percentile_10": 12.4,
        							"air_temperature_percentile_90": 15.3,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 30.5,
        							"cloud_area_fraction_medium": 96.5,
        							"dew_point_temperature": 9.9,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 81.4,
        							"ultraviolet_index_clear_sky": 1.2,
        							"wind_from_direction": 218.3,
        							"wind_speed": 3.8,
        							"wind_speed_of_gust": 7.4,
        							"wind_speed_percentile_10": 0.9,
        							"wind_speed_percentile_90": 3.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 55.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 3.2,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "lightrain"
        						},
        						"details": {
        							"air_temperature_max": 12.8,
        							"air_temperature_min": 11.5,
        							"precipitation_amount": 0.6,
        							"precipitation_amount_max": 4.8,
        							"precipitation_amount_min": 0.1,
        							"probability_of_precipitation": 50.4
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T17:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1015.2,
        							"air_temperature": 12.8,
        							"air_temperature_percentile_10": 11.8,
        							"air_temperature_percentile_90": 14.5,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 49.0,
        							"cloud_area_fraction_medium": 96.9,
        							"dew_point_temperature": 10.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 89.8,
        							"ultraviolet_index_clear_sky": 0.6,
        							"wind_from_direction": 246.4,
        							"wind_speed": 3.5,
        							"wind_speed_of_gust": 7.9,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 3.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 55.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 4.2,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rain"
        						},
        						"details": {
        							"air_temperature_max": 12.5,
        							"air_temperature_min": 11.5,
        							"precipitation_amount": 2.2,
        							"precipitation_amount_max": 6.3,
        							"precipitation_amount_min": 0.1,
        							"probability_of_precipitation": 55.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1014.5,
        							"air_temperature": 12.5,
        							"air_temperature_percentile_10": 11.2,
        							"air_temperature_percentile_90": 14.0,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.5,
        							"cloud_area_fraction_low": 47.0,
        							"cloud_area_fraction_medium": 93.6,
        							"dew_point_temperature": 10.5,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 91.1,
        							"ultraviolet_index_clear_sky": 0.2,
        							"wind_from_direction": 235.2,
        							"wind_speed": 2.9,
        							"wind_speed_of_gust": 6.8,
        							"wind_speed_percentile_10": 1.7,
        							"wind_speed_percentile_90": 3.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 55.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.3,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 23.2,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rain"
        						},
        						"details": {
        							"air_temperature_max": 12.1,
        							"air_temperature_min": 11.5,
        							"precipitation_amount": 2.6,
        							"precipitation_amount_max": 6.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 55.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T19:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1014.3,
        							"air_temperature": 12.1,
        							"air_temperature_percentile_10": 10.9,
        							"air_temperature_percentile_90": 13.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 30.1,
        							"cloud_area_fraction_medium": 99.2,
        							"dew_point_temperature": 10.5,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 92.6,
        							"ultraviolet_index_clear_sky": 0.1,
        							"wind_from_direction": 232.8,
        							"wind_speed": 2.7,
        							"wind_speed_of_gust": 5.6,
        							"wind_speed_percentile_10": 1.6,
        							"wind_speed_percentile_90": 3.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 55.2
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.1,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 40.2,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rain"
        						},
        						"details": {
        							"air_temperature_max": 12.1,
        							"air_temperature_min": 11.3,
        							"precipitation_amount": 2.6,
        							"precipitation_amount_max": 6.2,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 55.2
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T20:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.9,
        							"air_temperature": 12.1,
        							"air_temperature_percentile_10": 10.7,
        							"air_temperature_percentile_90": 13.0,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 20.3,
        							"cloud_area_fraction_medium": 99.5,
        							"dew_point_temperature": 10.0,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 90.3,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 236.7,
        							"wind_speed": 3.2,
        							"wind_speed_of_gust": 5.9,
        							"wind_speed_percentile_10": 1.4,
        							"wind_speed_percentile_90": 3.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 52.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.3,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 42.5,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rainshowers_night"
        						},
        						"details": {
        							"air_temperature_max": 11.9,
        							"air_temperature_min": 10.3,
        							"precipitation_amount": 2.6,
        							"precipitation_amount_max": 4.6,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 52.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T21:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.9,
        							"air_temperature": 11.9,
        							"air_temperature_percentile_10": 10.6,
        							"air_temperature_percentile_90": 12.8,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 41.3,
        							"cloud_area_fraction_medium": 100.0,
        							"dew_point_temperature": 10.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 95.7,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 230.1,
        							"wind_speed": 2.6,
        							"wind_speed_of_gust": 6.0,
        							"wind_speed_percentile_10": 0.9,
        							"wind_speed_percentile_90": 3.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 49.2
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "rain"
        						},
        						"details": {
        							"precipitation_amount": 0.5,
        							"precipitation_amount_max": 1.9,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 57.9,
        							"probability_of_thunder": 0.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rainshowers_night"
        						},
        						"details": {
        							"air_temperature_max": 11.5,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 2.5,
        							"precipitation_amount_max": 3.4,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 49.2
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T22:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.6,
        							"air_temperature": 11.5,
        							"air_temperature_percentile_10": 10.5,
        							"air_temperature_percentile_90": 12.6,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 36.4,
        							"cloud_area_fraction_medium": 100.0,
        							"dew_point_temperature": 10.8,
        							"fog_area_fraction": 0.5,
        							"relative_humidity": 98.7,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 264.7,
        							"wind_speed": 2.0,
        							"wind_speed_of_gust": 4.7,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.9
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrainshowers_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 36.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "heavyrain"
        						},
        						"details": {
        							"precipitation_amount": 1.0,
        							"precipitation_amount_max": 1.3,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 49.4,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rainshowers_night"
        						},
        						"details": {
        							"air_temperature_max": 11.5,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 1.4,
        							"precipitation_amount_max": 1.7,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 36.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-19T23:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.4,
        							"air_temperature": 11.5,
        							"air_temperature_percentile_10": 10.3,
        							"air_temperature_percentile_90": 12.6,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 19.0,
        							"cloud_area_fraction_medium": 100.0,
        							"dew_point_temperature": 10.9,
        							"fog_area_fraction": 1.1,
        							"relative_humidity": 99.2,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 240.9,
        							"wind_speed": 1.0,
        							"wind_speed_of_gust": 4.5,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 2.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 7.5
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.4,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 28.5,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_night"
        						},
        						"details": {
        							"air_temperature_max": 12.2,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 7.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.0,
        							"air_temperature": 11.5,
        							"air_temperature_percentile_10": 9.9,
        							"air_temperature_percentile_90": 12.2,
        							"cloud_area_fraction": 98.0,
        							"cloud_area_fraction_high": 69.6,
        							"cloud_area_fraction_low": 1.9,
        							"cloud_area_fraction_medium": 96.8,
        							"dew_point_temperature": 11.0,
        							"fog_area_fraction": 2.8,
        							"relative_humidity": 99.7,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 174.0,
        							"wind_speed": 0.7,
        							"wind_speed_of_gust": 1.8,
        							"wind_speed_percentile_10": 0.8,
        							"wind_speed_percentile_90": 2.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.7
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 4.8,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 14.4,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T01:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1012.8,
        							"air_temperature": 11.3,
        							"air_temperature_percentile_10": 9.6,
        							"air_temperature_percentile_90": 12.0,
        							"cloud_area_fraction": 46.6,
        							"cloud_area_fraction_high": 11.9,
        							"cloud_area_fraction_low": 2.8,
        							"cloud_area_fraction_medium": 39.4,
        							"dew_point_temperature": 10.8,
        							"fog_area_fraction": 1.3,
        							"relative_humidity": 99.4,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 287.4,
        							"wind_speed": 1.4,
        							"wind_speed_of_gust": 3.1,
        							"wind_speed_percentile_10": 0.8,
        							"wind_speed_percentile_90": 2.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_night"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 2.6,
        							"probability_of_thunder": 0.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 16.4,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T02:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1012.8,
        							"air_temperature": 10.3,
        							"air_temperature_percentile_10": 8.0,
        							"air_temperature_percentile_90": 11.6,
        							"cloud_area_fraction": 15.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 3.4,
        							"cloud_area_fraction_medium": 6.4,
        							"dew_point_temperature": 9.9,
        							"fog_area_fraction": 1.7,
        							"relative_humidity": 99.5,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 294.8,
        							"wind_speed": 1.5,
        							"wind_speed_of_gust": 2.6,
        							"wind_speed_percentile_10": 0.9,
        							"wind_speed_percentile_90": 2.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.6
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "fair_night"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 18.1,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T03:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.0,
        							"air_temperature": 9.5,
        							"air_temperature_percentile_10": 6.8,
        							"air_temperature_percentile_90": 11.2,
        							"cloud_area_fraction": 5.8,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.6,
        							"cloud_area_fraction_medium": 0.2,
        							"dew_point_temperature": 9.2,
        							"fog_area_fraction": 6.8,
        							"relative_humidity": 99.9,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 301.7,
        							"wind_speed": 2.0,
        							"wind_speed_of_gust": 3.6,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.6
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 18.6,
        							"air_temperature_min": 10.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T04:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.0,
        							"air_temperature": 10.6,
        							"air_temperature_percentile_10": 7.5,
        							"air_temperature_percentile_90": 12.0,
        							"cloud_area_fraction": 2.7,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.1,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 91.4,
        							"ultraviolet_index_clear_sky": 0.2,
        							"wind_from_direction": 328.5,
        							"wind_speed": 1.9,
        							"wind_speed_of_gust": 3.8,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.6
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T05:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.2,
        							"air_temperature": 12.2,
        							"air_temperature_percentile_10": 9.7,
        							"air_temperature_percentile_90": 13.3,
        							"cloud_area_fraction": 0.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.9,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 86.8,
        							"ultraviolet_index_clear_sky": 0.4,
        							"wind_from_direction": 317.2,
        							"wind_speed": 1.8,
        							"wind_speed_of_gust": 3.5,
        							"wind_speed_percentile_10": 1.1,
        							"wind_speed_percentile_90": 2.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.7
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 14.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.4,
        							"air_temperature": 14.4,
        							"air_temperature_percentile_10": 12.8,
        							"air_temperature_percentile_90": 15.2,
        							"cloud_area_fraction": 0.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.8,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 75.4,
        							"ultraviolet_index_clear_sky": 0.9,
        							"wind_from_direction": 334.2,
        							"wind_speed": 1.7,
        							"wind_speed_of_gust": 3.8,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.7
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 16.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T07:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.5,
        							"air_temperature": 16.4,
        							"air_temperature_percentile_10": 14.3,
        							"air_temperature_percentile_90": 17.2,
        							"cloud_area_fraction": 0.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 8.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 62.0,
        							"ultraviolet_index_clear_sky": 1.7,
        							"wind_from_direction": 8.7,
        							"wind_speed": 2.2,
        							"wind_speed_of_gust": 4.8,
        							"wind_speed_percentile_10": 1.3,
        							"wind_speed_percentile_90": 2.9
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 18.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T08:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.7,
        							"air_temperature": 18.1,
        							"air_temperature_percentile_10": 14.5,
        							"air_temperature_percentile_90": 19.1,
        							"cloud_area_fraction": 0.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 58.8,
        							"ultraviolet_index_clear_sky": 2.7,
        							"wind_from_direction": 29.5,
        							"wind_speed": 1.7,
        							"wind_speed_of_gust": 5.2,
        							"wind_speed_percentile_10": 1.7,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.8
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 17.8,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T09:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.6,
        							"air_temperature": 18.6,
        							"air_temperature_percentile_10": 14.3,
        							"air_temperature_percentile_90": 20.1,
        							"cloud_area_fraction": 0.0,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 60.6,
        							"ultraviolet_index_clear_sky": 3.6,
        							"wind_from_direction": 143.2,
        							"wind_speed": 1.9,
        							"wind_speed_of_gust": 5.0,
        							"wind_speed_percentile_10": 1.8,
        							"wind_speed_percentile_90": 2.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 0.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 18.7,
        							"air_temperature_min": 16.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T10:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.7,
        							"air_temperature": 18.7,
        							"air_temperature_percentile_10": 14.3,
        							"air_temperature_percentile_90": 19.4,
        							"cloud_area_fraction": 8.7,
        							"cloud_area_fraction_high": 8.2,
        							"cloud_area_fraction_low": 0.6,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 10.4,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 61.4,
        							"ultraviolet_index_clear_sky": 4.4,
        							"wind_from_direction": 180.6,
        							"wind_speed": 2.5,
        							"wind_speed_of_gust": 6.1,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 3.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 2.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 18.5,
        							"air_temperature_min": 15.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T11:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.7,
        							"air_temperature": 18.5,
        							"air_temperature_percentile_10": 14.5,
        							"air_temperature_percentile_90": 19.3,
        							"cloud_area_fraction": 18.8,
        							"cloud_area_fraction_high": 13.1,
        							"cloud_area_fraction_low": 6.1,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 10.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 61.6,
        							"ultraviolet_index_clear_sky": 4.7,
        							"wind_from_direction": 188.9,
        							"wind_speed": 2.7,
        							"wind_speed_of_gust": 7.0,
        							"wind_speed_percentile_10": 2.6,
        							"wind_speed_percentile_90": 4.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 10.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "fair_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 18.4,
        							"air_temperature_min": 13.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.7,
        							"air_temperature": 18.4,
        							"air_temperature_percentile_10": 14.3,
        							"air_temperature_percentile_90": 19.1,
        							"cloud_area_fraction": 15.6,
        							"cloud_area_fraction_high": 11.0,
        							"cloud_area_fraction_low": 4.6,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 60.0,
        							"ultraviolet_index_clear_sky": 4.6,
        							"wind_from_direction": 203.9,
        							"wind_speed": 3.1,
        							"wind_speed_of_gust": 7.3,
        							"wind_speed_percentile_10": 2.4,
        							"wind_speed_percentile_90": 4.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 14.7
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "fair_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 1.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 18.1,
        							"air_temperature_min": 12.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T13:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.2,
        							"air_temperature": 18.1,
        							"air_temperature_percentile_10": 13.6,
        							"air_temperature_percentile_90": 18.8,
        							"cloud_area_fraction": 97.3,
        							"cloud_area_fraction_high": 97.1,
        							"cloud_area_fraction_low": 3.8,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.0,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 58.0,
        							"ultraviolet_index_clear_sky": 4.0,
        							"wind_from_direction": 212.0,
        							"wind_speed": 3.0,
        							"wind_speed_of_gust": 7.4,
        							"wind_speed_percentile_10": 2.0,
        							"wind_speed_percentile_90": 4.3
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 1.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 17.8,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T14:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1012.8,
        							"air_temperature": 17.8,
        							"air_temperature_percentile_10": 14.5,
        							"air_temperature_percentile_90": 18.4,
        							"cloud_area_fraction": 99.1,
        							"cloud_area_fraction_high": 99.0,
        							"cloud_area_fraction_low": 2.9,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 9.3,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 59.7,
        							"ultraviolet_index_clear_sky": 3.1,
        							"wind_from_direction": 208.2,
        							"wind_speed": 2.5,
        							"wind_speed_of_gust": 6.8,
        							"wind_speed_percentile_10": 2.4,
        							"wind_speed_percentile_90": 3.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.6,
        							"air_temperature_min": 11.9,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T15:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1012.6,
        							"air_temperature": 16.6,
        							"air_temperature_percentile_10": 13.4,
        							"air_temperature_percentile_90": 17.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 14.3,
        							"cloud_area_fraction_medium": 0.1,
        							"dew_point_temperature": 8.2,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 59.4,
        							"ultraviolet_index_clear_sky": 2.1,
        							"wind_from_direction": 203.6,
        							"wind_speed": 2.3,
        							"wind_speed_of_gust": 6.0,
        							"wind_speed_percentile_10": 2.1,
        							"wind_speed_percentile_90": 5.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.4
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 15.2,
        							"air_temperature_min": 11.7,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T16:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1012.3,
        							"air_temperature": 15.2,
        							"air_temperature_percentile_10": 13.0,
        							"air_temperature_percentile_90": 16.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 73.9,
        							"cloud_area_fraction_medium": 1.4,
        							"dew_point_temperature": 8.1,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 64.6,
        							"ultraviolet_index_clear_sky": 1.2,
        							"wind_from_direction": 135.2,
        							"wind_speed": 3.3,
        							"wind_speed_of_gust": 6.4,
        							"wind_speed_percentile_10": 1.5,
        							"wind_speed_percentile_90": 5.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.3,
        							"probability_of_thunder": 0.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.2,
        							"air_temperature_min": 11.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 1.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T17:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.8,
        							"air_temperature": 13.2,
        							"air_temperature_percentile_10": 12.3,
        							"air_temperature_percentile_90": 15.1,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 57.3,
        							"cloud_area_fraction_medium": 14.9,
        							"dew_point_temperature": 9.9,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 83.2,
        							"ultraviolet_index_clear_sky": 0.6,
        							"wind_from_direction": 113.7,
        							"wind_speed": 3.5,
        							"wind_speed_of_gust": 8.0,
        							"wind_speed_percentile_10": 2.0,
        							"wind_speed_percentile_90": 5.5
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.7,
        							"probability_of_thunder": 0.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 12.4,
        							"air_temperature_min": 11.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 8.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.6,
        							"air_temperature": 12.4,
        							"air_temperature_percentile_10": 10.8,
        							"air_temperature_percentile_90": 14.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 45.6,
        							"cloud_area_fraction_medium": 45.0,
        							"dew_point_temperature": 10.1,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 88.8,
        							"ultraviolet_index_clear_sky": 0.2,
        							"wind_from_direction": 116.5,
        							"wind_speed": 3.6,
        							"wind_speed_of_gust": 7.0,
        							"wind_speed_percentile_10": 1.8,
        							"wind_speed_percentile_90": 5.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 47.1
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.3,
        							"probability_of_thunder": 0.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 12.2,
        							"air_temperature_min": 11.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.1,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 14.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T19:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.2,
        							"air_temperature": 12.2,
        							"air_temperature_percentile_10": 10.9,
        							"air_temperature_percentile_90": 13.8,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 62.2,
        							"cloud_area_fraction_medium": 51.8,
        							"dew_point_temperature": 9.6,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 87.5,
        							"ultraviolet_index_clear_sky": 0.1,
        							"wind_from_direction": 114.1,
        							"wind_speed": 3.8,
        							"wind_speed_of_gust": 7.2,
        							"wind_speed_percentile_10": 1.9,
        							"wind_speed_percentile_90": 5.7
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 0.0,
        							"probability_of_thunder": 0.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T20:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1010.8,
        							"air_temperature": 11.9,
        							"air_temperature_percentile_10": 10.6,
        							"air_temperature_percentile_90": 13.6,
        							"cloud_area_fraction": 94.8,
        							"cloud_area_fraction_high": 70.5,
        							"cloud_area_fraction_low": 69.1,
        							"cloud_area_fraction_medium": 61.8,
        							"dew_point_temperature": 9.7,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 89.6,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 123.6,
        							"wind_speed": 3.4,
        							"wind_speed_of_gust": 7.4,
        							"wind_speed_percentile_10": 2.8,
        							"wind_speed_percentile_90": 4.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 1.2,
        							"probability_of_thunder": 0.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T21:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1010.2,
        							"air_temperature": 11.7,
        							"air_temperature_percentile_10": 10.5,
        							"air_temperature_percentile_90": 13.3,
        							"cloud_area_fraction": 99.1,
        							"cloud_area_fraction_high": 83.7,
        							"cloud_area_fraction_low": 74.8,
        							"cloud_area_fraction_medium": 80.2,
        							"dew_point_temperature": 9.6,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 89.7,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 106.7,
        							"wind_speed": 3.0,
        							"wind_speed_of_gust": 6.4,
        							"wind_speed_percentile_10": 2.7,
        							"wind_speed_percentile_90": 5.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 5.9,
        							"probability_of_thunder": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T22:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.8,
        							"air_temperature": 11.6,
        							"air_temperature_percentile_10": 10.4,
        							"air_temperature_percentile_90": 13.0,
        							"cloud_area_fraction": 99.8,
        							"cloud_area_fraction_high": 68.4,
        							"cloud_area_fraction_low": 76.4,
        							"cloud_area_fraction_medium": 98.7,
        							"dew_point_temperature": 9.6,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 90.3,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 89.5,
        							"wind_speed": 3.3,
        							"wind_speed_of_gust": 6.3,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 5.0
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.2,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 12.5,
        							"probability_of_thunder": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-20T23:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.4,
        							"air_temperature": 11.6,
        							"air_temperature_percentile_10": 10.4,
        							"air_temperature_percentile_90": 12.7,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 97.6,
        							"cloud_area_fraction_low": 80.1,
        							"cloud_area_fraction_medium": 99.8,
        							"dew_point_temperature": 10.0,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 92.0,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 88.4,
        							"wind_speed": 3.1,
        							"wind_speed_of_gust": 6.3,
        							"wind_speed_percentile_10": 3.0,
        							"wind_speed_percentile_90": 5.9
        						}
        					},
        					"next_1_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.5,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 26.3,
        							"probability_of_thunder": 0.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-21T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1008.8,
        							"air_temperature": 11.7,
        							"air_temperature_percentile_10": 10.0,
        							"air_temperature_percentile_90": 12.3,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.7,
        							"cloud_area_fraction_low": 80.7,
        							"cloud_area_fraction_medium": 99.6,
        							"dew_point_temperature": 10.0,
        							"fog_area_fraction": 0.0,
        							"relative_humidity": 91.1,
        							"ultraviolet_index_clear_sky": 0.0,
        							"wind_from_direction": 73.7,
        							"wind_speed": 3.9,
        							"wind_speed_of_gust": 7.3,
        							"wind_speed_percentile_10": 3.1,
        							"wind_speed_percentile_90": 6.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "lightrain",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 47.1
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "rain"
        						},
        						"details": {
        							"air_temperature_max": 10.8,
        							"air_temperature_min": 9.7,
        							"precipitation_amount": 2.0,
        							"precipitation_amount_max": 3.8,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 45.1
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-21T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1008.4,
        							"air_temperature": 10.0,
        							"air_temperature_percentile_10": 9.5,
        							"air_temperature_percentile_90": 11.3,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.2,
        							"cloud_area_fraction_low": 71.9,
        							"cloud_area_fraction_medium": 100.0,
        							"dew_point_temperature": 7.3,
        							"relative_humidity": 83.3,
        							"wind_from_direction": 188.0,
        							"wind_speed": 2.3,
        							"wind_speed_percentile_10": 1.8,
        							"wind_speed_percentile_90": 3.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 25.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.4,
        							"air_temperature_min": 11.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 21.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-21T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.0,
        							"air_temperature": 13.3,
        							"air_temperature_percentile_10": 11.6,
        							"air_temperature_percentile_90": 14.9,
        							"cloud_area_fraction": 87.5,
        							"cloud_area_fraction_high": 1.6,
        							"cloud_area_fraction_low": 41.4,
        							"cloud_area_fraction_medium": 70.3,
        							"dew_point_temperature": 8.0,
        							"relative_humidity": 70.4,
        							"wind_from_direction": 179.2,
        							"wind_speed": 3.1,
        							"wind_speed_percentile_10": 1.7,
        							"wind_speed_percentile_90": 4.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 11.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 16.8,
        							"air_temperature_min": 15.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 7.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-21T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.2,
        							"air_temperature": 15.9,
        							"air_temperature_percentile_10": 14.0,
        							"air_temperature_percentile_90": 18.4,
        							"cloud_area_fraction": 34.8,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 5.5,
        							"cloud_area_fraction_medium": 27.7,
        							"dew_point_temperature": 11.7,
        							"relative_humidity": 75.5,
        							"wind_from_direction": 172.4,
        							"wind_speed": 2.1,
        							"wind_speed_percentile_10": 1.4,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 5.9
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_night"
        						},
        						"details": {
        							"air_temperature_max": 14.2,
        							"air_temperature_min": 11.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 3.9
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-22T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1010.4,
        							"air_temperature": 11.3,
        							"air_temperature_percentile_10": 10.0,
        							"air_temperature_percentile_90": 12.8,
        							"cloud_area_fraction": 41.4,
        							"cloud_area_fraction_high": 7.8,
        							"cloud_area_fraction_low": 2.3,
        							"cloud_area_fraction_medium": 5.5,
        							"dew_point_temperature": 9.3,
        							"relative_humidity": 87.2,
        							"wind_from_direction": 238.4,
        							"wind_speed": 1.4,
        							"wind_speed_percentile_10": 1.0,
        							"wind_speed_percentile_90": 2.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 9.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 10.4,
        							"air_temperature_min": 8.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 2.0
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-22T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.3,
        							"air_temperature": 10.6,
        							"air_temperature_percentile_10": 9.4,
        							"air_temperature_percentile_90": 12.0,
        							"cloud_area_fraction": 75.0,
        							"cloud_area_fraction_high": 32.8,
        							"cloud_area_fraction_low": 7.8,
        							"cloud_area_fraction_medium": 3.9,
        							"dew_point_temperature": 6.9,
        							"relative_humidity": 77.9,
        							"wind_from_direction": 250.1,
        							"wind_speed": 1.9,
        							"wind_speed_percentile_10": 1.1,
        							"wind_speed_percentile_90": 2.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 9.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 13.4,
        							"air_temperature_min": 11.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 5.9
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-22T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.7,
        							"air_temperature": 13.2,
        							"air_temperature_percentile_10": 12.5,
        							"air_temperature_percentile_90": 15.4,
        							"cloud_area_fraction": 69.9,
        							"cloud_area_fraction_high": 14.1,
        							"cloud_area_fraction_low": 12.1,
        							"cloud_area_fraction_medium": 34.4,
        							"dew_point_temperature": 8.9,
        							"relative_humidity": 75.0,
        							"wind_from_direction": 171.9,
        							"wind_speed": 3.5,
        							"wind_speed_percentile_10": 2.9,
        							"wind_speed_percentile_90": 4.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "certain"
        						},
        						"details": {
        							"probability_of_precipitation": 9.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 17.5,
        							"air_temperature_min": 15.9,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 3.9
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-22T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1010.2,
        							"air_temperature": 16.5,
        							"air_temperature_percentile_10": 15.0,
        							"air_temperature_percentile_90": 18.6,
        							"cloud_area_fraction": 96.9,
        							"cloud_area_fraction_high": 96.1,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 18.7,
        							"dew_point_temperature": 12.4,
        							"relative_humidity": 76.1,
        							"wind_from_direction": 179.2,
        							"wind_speed": 2.9,
        							"wind_speed_percentile_10": 2.3,
        							"wind_speed_percentile_90": 4.5
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 19.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 15.3,
        							"air_temperature_min": 12.8,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 7.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-23T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.2,
        							"air_temperature": 12.7,
        							"air_temperature_percentile_10": 11.7,
        							"air_temperature_percentile_90": 13.9,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 28.9,
        							"dew_point_temperature": 10.4,
        							"relative_humidity": 85.2,
        							"wind_from_direction": 232.0,
        							"wind_speed": 2.5,
        							"wind_speed_percentile_10": 1.7,
        							"wind_speed_percentile_90": 4.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 21.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 11.4,
        							"air_temperature_min": 10.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-23T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1008.1,
        							"air_temperature": 11.3,
        							"air_temperature_percentile_10": 10.4,
        							"air_temperature_percentile_90": 12.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 14.8,
        							"cloud_area_fraction_medium": 43.7,
        							"dew_point_temperature": 8.4,
        							"relative_humidity": 82.0,
        							"wind_from_direction": 223.7,
        							"wind_speed": 3.5,
        							"wind_speed_percentile_10": 2.0,
        							"wind_speed_percentile_90": 4.9
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 29.4
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.0,
        							"air_temperature_min": 12.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-23T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1007.1,
        							"air_temperature": 13.5,
        							"air_temperature_percentile_10": 12.3,
        							"air_temperature_percentile_90": 15.2,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 14.8,
        							"cloud_area_fraction_medium": 79.7,
        							"dew_point_temperature": 9.4,
        							"relative_humidity": 76.1,
        							"wind_from_direction": 198.3,
        							"wind_speed": 6.3,
        							"wind_speed_percentile_10": 4.2,
        							"wind_speed_percentile_90": 7.4
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 33.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 17.1,
        							"air_temperature_min": 15.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-23T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1005.2,
        							"air_temperature": 15.7,
        							"air_temperature_percentile_10": 14.3,
        							"air_temperature_percentile_90": 17.4,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 100.0,
        							"cloud_area_fraction_low": 4.7,
        							"cloud_area_fraction_medium": 51.6,
        							"dew_point_temperature": 12.3,
        							"relative_humidity": 79.8,
        							"wind_from_direction": 203.0,
        							"wind_speed": 4.8,
        							"wind_speed_percentile_10": 3.6,
        							"wind_speed_percentile_90": 5.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 35.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.1,
        							"air_temperature_min": 12.7,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 23.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-24T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1004.3,
        							"air_temperature": 12.6,
        							"air_temperature_percentile_10": 11.6,
        							"air_temperature_percentile_90": 13.2,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 97.3,
        							"cloud_area_fraction_low": 6.2,
        							"cloud_area_fraction_medium": 70.3,
        							"dew_point_temperature": 10.5,
        							"relative_humidity": 86.8,
        							"wind_from_direction": 216.2,
        							"wind_speed": 3.0,
        							"wind_speed_percentile_10": 2.2,
        							"wind_speed_percentile_90": 4.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 39.2
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 11.6,
        							"air_temperature_min": 10.3,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.7,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 27.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-24T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1002.7,
        							"air_temperature": 11.6,
        							"air_temperature_percentile_10": 10.0,
        							"air_temperature_percentile_90": 12.1,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 98.4,
        							"cloud_area_fraction_low": 21.9,
        							"cloud_area_fraction_medium": 35.9,
        							"dew_point_temperature": 8.7,
        							"relative_humidity": 82.0,
        							"wind_from_direction": 155.5,
        							"wind_speed": 3.3,
        							"wind_speed_percentile_10": 2.2,
        							"wind_speed_percentile_90": 4.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 33.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 13.7,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.7,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 27.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-24T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1002.5,
        							"air_temperature": 13.3,
        							"air_temperature_percentile_10": 12.2,
        							"air_temperature_percentile_90": 14.9,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.2,
        							"cloud_area_fraction_low": 24.2,
        							"cloud_area_fraction_medium": 50.8,
        							"dew_point_temperature": 9.0,
        							"relative_humidity": 75.1,
        							"wind_from_direction": 173.1,
        							"wind_speed": 3.9,
        							"wind_speed_percentile_10": 2.9,
        							"wind_speed_percentile_90": 5.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 37.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 17.3,
        							"air_temperature_min": 15.6,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.8,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 27.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-24T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 998.9,
        							"air_temperature": 16.5,
        							"air_temperature_percentile_10": 14.4,
        							"air_temperature_percentile_90": 18.5,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 99.2,
        							"cloud_area_fraction_low": 0.8,
        							"cloud_area_fraction_medium": 17.2,
        							"dew_point_temperature": 12.2,
        							"relative_humidity": 75.2,
        							"wind_from_direction": 185.5,
        							"wind_speed": 2.6,
        							"wind_speed_percentile_10": 2.0,
        							"wind_speed_percentile_90": 4.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 37.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.8,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 1.1,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 31.4
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-25T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 999.0,
        							"air_temperature": 12.3,
        							"air_temperature_percentile_10": 11.2,
        							"air_temperature_percentile_90": 14.1,
        							"cloud_area_fraction": 100.0,
        							"cloud_area_fraction_high": 98.8,
        							"cloud_area_fraction_low": 2.3,
        							"cloud_area_fraction_medium": 91.8,
        							"dew_point_temperature": 9.6,
        							"relative_humidity": 83.4,
        							"wind_from_direction": 219.8,
        							"wind_speed": 3.4,
        							"wind_speed_percentile_10": 1.9,
        							"wind_speed_percentile_90": 5.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "cloudy",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 35.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 11.5,
        							"air_temperature_min": 10.7,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-25T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 999.7,
        							"air_temperature": 11.4,
        							"air_temperature_percentile_10": 10.0,
        							"air_temperature_percentile_90": 12.4,
        							"cloud_area_fraction": 99.2,
        							"cloud_area_fraction_high": 93.7,
        							"cloud_area_fraction_low": 11.7,
        							"cloud_area_fraction_medium": 33.6,
        							"dew_point_temperature": 7.5,
        							"relative_humidity": 77.4,
        							"wind_from_direction": 216.9,
        							"wind_speed": 3.3,
        							"wind_speed_percentile_10": 2.2,
        							"wind_speed_percentile_90": 5.1
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 33.3
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 14.2,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 23.5
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-25T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1000.1,
        							"air_temperature": 13.7,
        							"air_temperature_percentile_10": 12.5,
        							"air_temperature_percentile_90": 15.5,
        							"cloud_area_fraction": 93.7,
        							"cloud_area_fraction_high": 37.5,
        							"cloud_area_fraction_low": 17.2,
        							"cloud_area_fraction_medium": 39.1,
        							"dew_point_temperature": 9.2,
        							"relative_humidity": 74.0,
        							"wind_from_direction": 196.0,
        							"wind_speed": 4.6,
        							"wind_speed_percentile_10": 3.3,
        							"wind_speed_percentile_90": 7.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 21.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "cloudy"
        						},
        						"details": {
        							"air_temperature_max": 17.3,
        							"air_temperature_min": 15.8,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 15.7
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-25T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1000.8,
        							"air_temperature": 16.9,
        							"air_temperature_percentile_10": 14.6,
        							"air_temperature_percentile_90": 18.6,
        							"cloud_area_fraction": 48.4,
        							"cloud_area_fraction_high": 15.6,
        							"cloud_area_fraction_low": 0.8,
        							"cloud_area_fraction_medium": 25.0,
        							"dew_point_temperature": 12.1,
        							"relative_humidity": 72.8,
        							"wind_from_direction": 215.3,
        							"wind_speed": 3.9,
        							"wind_speed_percentile_10": 2.2,
        							"wind_speed_percentile_90": 6.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 23.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_night"
        						},
        						"details": {
        							"air_temperature_max": 14.5,
        							"air_temperature_min": 12.0,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 9.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-26T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1002.3,
        							"air_temperature": 12.0,
        							"air_temperature_percentile_10": 10.6,
        							"air_temperature_percentile_90": 13.9,
        							"cloud_area_fraction": 23.0,
        							"cloud_area_fraction_high": 6.6,
        							"cloud_area_fraction_low": 0.8,
        							"cloud_area_fraction_medium": 7.4,
        							"dew_point_temperature": 8.7,
        							"relative_humidity": 80.2,
        							"wind_from_direction": 259.4,
        							"wind_speed": 3.8,
        							"wind_speed_percentile_10": 1.9,
        							"wind_speed_percentile_90": 5.6
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 25.5
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_day"
        						},
        						"details": {
        							"air_temperature_max": 11.4,
        							"air_temperature_min": 9.5,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 19.6
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-26T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1008.1,
        							"air_temperature": 11.6,
        							"air_temperature_percentile_10": 9.9,
        							"air_temperature_percentile_90": 13.0,
        							"cloud_area_fraction": 3.1,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.8,
        							"dew_point_temperature": 5.2,
        							"relative_humidity": 65.1,
        							"wind_from_direction": 253.2,
        							"wind_speed": 4.1,
        							"wind_speed_percentile_10": 2.2,
        							"wind_speed_percentile_90": 6.7
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 15.7
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 15.9,
        							"air_temperature_min": 12.3,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 11.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-26T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1009.8,
        							"air_temperature": 15.7,
        							"air_temperature_percentile_10": 13.1,
        							"air_temperature_percentile_90": 17.4,
        							"cloud_area_fraction": 42.2,
        							"cloud_area_fraction_high": 1.6,
        							"cloud_area_fraction_low": 1.6,
        							"cloud_area_fraction_medium": 21.9,
        							"dew_point_temperature": 6.8,
        							"relative_humidity": 55.3,
        							"wind_from_direction": 213.8,
        							"wind_speed": 5.7,
        							"wind_speed_percentile_10": 3.5,
        							"wind_speed_percentile_90": 8.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 19.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "partlycloudy_day"
        						},
        						"details": {
        							"air_temperature_max": 19.6,
        							"air_temperature_min": 18.1,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 9.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-26T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1011.3,
        							"air_temperature": 18.1,
        							"air_temperature_percentile_10": 15.2,
        							"air_temperature_percentile_90": 19.6,
        							"cloud_area_fraction": 19.5,
        							"cloud_area_fraction_high": 0.8,
        							"cloud_area_fraction_low": 0.8,
        							"cloud_area_fraction_medium": 7.0,
        							"dew_point_temperature": 10.6,
        							"relative_humidity": 61.2,
        							"wind_from_direction": 225.5,
        							"wind_speed": 5.4,
        							"wind_speed_percentile_10": 2.9,
        							"wind_speed_percentile_90": 7.8
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 11.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_night"
        						},
        						"details": {
        							"air_temperature_max": 16.4,
        							"air_temperature_min": 12.4,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 11.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-27T00:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1013.5,
        							"air_temperature": 12.3,
        							"air_temperature_percentile_10": 11.0,
        							"air_temperature_percentile_90": 14.5,
        							"cloud_area_fraction": 3.1,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 0.8,
        							"dew_point_temperature": 7.9,
        							"relative_humidity": 74.4,
        							"wind_from_direction": 246.2,
        							"wind_speed": 4.7,
        							"wind_speed_percentile_10": 2.8,
        							"wind_speed_percentile_90": 6.3
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day",
        							"symbol_confidence": "uncertain"
        						},
        						"details": {
        							"probability_of_precipitation": 11.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 11.1,
        							"air_temperature_min": 9.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 7.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-27T06:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1014.7,
        							"air_temperature": 11.2,
        							"air_temperature_percentile_10": 9.8,
        							"air_temperature_percentile_90": 13.2,
        							"cloud_area_fraction": 5.9,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.4,
        							"cloud_area_fraction_medium": 0.0,
        							"dew_point_temperature": 5.4,
        							"relative_humidity": 68.0,
        							"wind_from_direction": 243.7,
        							"wind_speed": 3.7,
        							"wind_speed_percentile_10": 2.1,
        							"wind_speed_percentile_90": 5.2
        						}
        					},
        					"next_12_hours": {
        						"summary": {
        							"symbol_code": "fair_day",
        							"symbol_confidence": "somewhat certain"
        						},
        						"details": {
        							"probability_of_precipitation": 19.6
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "clearsky_day"
        						},
        						"details": {
        							"air_temperature_max": 15.6,
        							"air_temperature_min": 12.2,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 9.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-27T12:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1014.4,
        							"air_temperature": 15.3,
        							"air_temperature_percentile_10": 12.4,
        							"air_temperature_percentile_90": 17.7,
        							"cloud_area_fraction": 26.6,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.8,
        							"cloud_area_fraction_medium": 6.2,
        							"dew_point_temperature": 6.7,
        							"relative_humidity": 56.5,
        							"wind_from_direction": 213.8,
        							"wind_speed": 6.2,
        							"wind_speed_percentile_10": 3.6,
        							"wind_speed_percentile_90": 7.8
        						}
        					},
        					"next_6_hours": {
        						"summary": {
        							"symbol_code": "fair_day"
        						},
        						"details": {
        							"air_temperature_max": 19.7,
        							"air_temperature_min": 17.8,
        							"precipitation_amount": 0.0,
        							"precipitation_amount_max": 0.0,
        							"precipitation_amount_min": 0.0,
        							"probability_of_precipitation": 11.8
        						}
        					}
        				}
        			},
        			{
        				"time": "2022-05-27T18:00:00Z",
        				"data": {
        					"instant": {
        						"details": {
        							"air_pressure_at_sea_level": 1016.4,
        							"air_temperature": 18.0,
        							"air_temperature_percentile_10": 15.7,
        							"air_temperature_percentile_90": 20.6,
        							"cloud_area_fraction": 7.4,
        							"cloud_area_fraction_high": 0.0,
        							"cloud_area_fraction_low": 0.0,
        							"cloud_area_fraction_medium": 2.3,
        							"dew_point_temperature": 9.5,
        							"relative_humidity": 57.1,
        							"wind_from_direction": 243.1,
        							"wind_speed": 5.3,
        							"wind_speed_percentile_10": 2.5,
        							"wind_speed_percentile_90": 7.0
        						}
        					}
        				}
        			}
        		]
        	}
        }
    """.trimIndent()
    private val exampleResponseObjet = Json.decodeFromString<WeatherData>(exampleResponseRaw)

    @Test
    fun isCorrectTemperature() {
        val temperature = exampleResponseObjet.properties.timeseries[0].data.instant.details?.air_temperature
        assertEquals(12.5, temperature)
    }

    @Test
    fun isWearingCorrectClothes() {
        val clothes = ClothesAlgorithm().getWeatherScore(exampleResponseObjet, 0)
        assertEquals(mutableListOf("", "tskjorte", "", "bukse", "sneakers", ""), clothes)
    }
}
