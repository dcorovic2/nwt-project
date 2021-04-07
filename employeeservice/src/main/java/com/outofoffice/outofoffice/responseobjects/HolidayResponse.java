package com.outofoffice.outofoffice.responseobjects;

import java.util.List;

import lombok.Data;

@Data
public class HolidayResponse {
        String name;
        Long id;
     
        public HolidayResponse() {}

		public HolidayResponse(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		};
        
     
}
