package com.office.library.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalBookDto {

	private int rb_no;
	private int b_no;
	private int u_m_no;
	private String rb_start_date;
	private String rb_end_date;
	private String rb_reg_date;
	private String rb_mod_date;
	
}
