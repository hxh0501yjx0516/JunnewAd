package com.pancou.ad.vo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ���ģʽģ��ʵ���� 
 *
 */

@Entity
public class Models implements Serializable {
	@Id
	@GeneratedValue
	private int	modelId;		//ID
	private String modelName;	//ģ�����
	private String modelJs;		//ģ���ļ����
	private int modelStatus;	//״̬	0 �C ��		1 �C��
	private int modelFlag; //����	0 �C �������1 �C������ 2 -����ͼƬ
	
	public int getModelFlag() {
		return modelFlag;
	}

	public void setModelFlag(int modelFlag) {
		this.modelFlag = modelFlag;
	}

	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	public int getModelId(){
		return modelId;
	}
	
	public void setModelName(String modelName){
		this.modelName = modelName;
	}
	
	public String getModelName(){
		return modelName;
	}
	
	public void setModelJs(String modelJs){
		this.modelJs = modelJs;
	}
	
	public String getModelJs(){
		return modelJs;
	}
	
	public void setModelStatus(int modelStatus){
		this.modelStatus = modelStatus;
	}
	
	public int getModelStatus(){
		return modelStatus;
	}
}
