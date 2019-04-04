package com.pancou.ad.form;

import javax.persistence.Entity;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@Entity
@SuppressWarnings("serial")
public class AdPlanForm extends ActionForm {
	private FormFile formFile;

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}
}
