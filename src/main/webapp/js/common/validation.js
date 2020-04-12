/**
 * 클라이언트 입력값 검증
 * 정규식 패턴 정의 : http://ryanswanson.com/regexp/#start
 */
// /^[a-z][0-9]{3}$/
// "a001".validationID();
String.prototype.validationID = function(){
	// this == "a001"
	return /^[a-z][0-9]{3}$/.test(this);
};
String.prototype.validationPWD = function(){
	return /^[a-z0-9]{4,8}$/.test(this);
};
String.prototype.validationNM = function(){
	return /^[가-힣]{2,5}$/.test(this);
};
// 111111-1111111.validationREGNO();
String.prototype.validationREGNO = function(){
	
	if(!/^[0-9]{6}-[0-9]{7}$/.test(this)){
		return false;
	}
	// 1 1 1 1 1 1 - 1 1 1 1 1 1 8
	// 1 1 1 1 1 1 - 1 1 1 1 1 1
	// * * * * * *   * * * * * *
	// 2+3+4+5+6+7 + 8+9+2+3+4+5 = 결과값
	// (11-(결과값%11))%10 = 최종값
	// if(최종값 == 매직넘버)
	//    유효한 주민등록번호
	// else
	//    무효한 주민등록번호
	var regno = this.replace('-', '');
	var magicNum = regno.substring(12, 13);
	var result = 0;
	var exp = [2,3,4,5,6,7,8,9,2,3,4,5];
	for(var i=0; i<12; i++){
		result += exp[i] * regno.substring(i, i+1);
	}
	result = (11 - (result % 11)) % 10;
	return result==magicNum?true:false;
};
String.prototype.validationBIR = function(){
	return /^(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\d|3[0-1])\s([0-1]\d|2[0-4]):([0-5][0-9]):([0-5][0-9])$/.test(this);
};
String.prototype.validationMAIL = function(){
	return /^[a-z]\d{3}@[a-z]+(\.[a-z]{2,3}){1,2}$/.test(this);
};
String.prototype.validationHOMETEL = function(){
	return /^(02|0[3-7][1-7])-\d{3,4}-\d{4}$/.test(this);
};
String.prototype.validationCOMTEL = function(){
	return /^(02|0[3-7][1-7])-\d{3,4}-\d{4}$/.test(this);
};
String.prototype.validationHP = function(){
	return /^(01[0-1]|01[6-9])-\d{3,4}-\d{4}$/.test(this);
};
String.prototype.validationZIPCODE = function(){
	return /^\d{3}-\d{3}$/.test(this);
};
String.prototype.validationADDR = function(){
	return /^[가-힣]{2,5}$/.test(this);
};

///////////////////////////02. 21 오늘 할 일///////////////////////////

// '042-1111-1111'.validationHOMETEL()
// 집 전화번호 : 지역번호 서울 두자리 그 외 지역 세자리
//			  국번 세자리 또는 네자리
//			   전화번호 네자리
//	'042-1111-1111'.validationCOMTEL()
//	회사전화번호 :  지역번호 서울 두자리 그 외 지역 세자리
//				국번 세자리 또는 네자리
//				전화번호 네자리
//	'010,011,016,017,018' - 국번 세자리 또는 네자리 - 전화번호 네자리'
//	'010-1111-1111'.validationHP()
//	우편번호
//	'111-111'.validationZIPCODE();

//	MemberFormController에 입력값 검증 적용
//	교재에 서블릿 부분까지 정독 복습
//	상시학습의 서블릿 부분 동영상 복습

String.prototype.validationNUM = function(){
	return /^\d+$/.test(this);
};
String.prototype.validationDATE = function(){
	return /^(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\d|3[0-1])$/.test(this);
};
String.prototype.validationMAIL2 = function(){
	return /^[a-zA-Z]+@[a-z]+(\.[a-z]{2,3}){1,2}$/.test(this);
};




