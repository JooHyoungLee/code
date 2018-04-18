function checkKor(temp, text, minLength, maxLength){
	//text(문자열), minLength(최소자리수) maxLength(최대자리수)
	//한글 체크
	var len = text.length;
	if(len >= minLength  && len  <= maxLength){
		var pattern = /^[가-힣\s]+$/;
		if(!pattern.test(text)){
			alert(temp+"는 한글만 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}else{
		alert(temp+"은 "+minLength+"자리 이상 "+maxLength+"자리 이하로 입력해주세요.");
		return false;
	}
}

function checkStreetName(text)
{
	var pattern=/^([0-9가-힣 ]+)$/;
	if(pattern.test(text))
	{
		return true;
	}
	else
	{
		alert('영문은 사용할 수 없습니다.');
		return false;
	}
}

function checkEng(temp, text, minLength, maxLength){
	//text(문자열), minLength(최소자리수) maxLength(최대자리수)
	//영대소문자 체크
	var len = text.length;
	if(len >= minLength  && len <= maxLength){

		var pattern = /^[a-zA-Z\s]+$/;
		if(!pattern.test(text)){
			alert(temp+"는 영문자만 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}else{
		alert(minLength+"자리 이상 "+maxLength+"자리 이하로 입력해주세요.");
	}
}

function checkLength(text, minLength, maxLength){
	//자리수 체크 text(문자열), minLength(최소자리수) maxLength(최대자리수)
	var len = text.length;
	if(len >= minLength  && len <= maxLength){
		//alert("성공");
		return true;
	}else{
		//alert(minLength+"자리 이상 "+maxLength+"자리 이하");
		return false;
	}
	
}

function checkPassword(text, minLength, maxLength){
	//text(문자열)
	var len = text.length;
	if(len  >= minLength && len <= maxLength){
		//비밀번호 체크
		var pattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,16}$/;
		if(!pattern.test(text)){
			//alert(temp+"는 대 소문자, 숫자, 특수문자가 하나이상 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}else{
		//alert(temp+"은 "+minLength+"자리 이상 "+maxLength+"자리 이하로 입력해주세요.");
		return false;
	}
}

function checkEmail(text){
	//text(문자열)
	// 이메일 체크
	var pattern = /[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
	if(!pattern.test(text)){
		return false;
	}else{
		return true;
	}
}

function checkPhone(phone){
	//phone(전화번호)
	//휴대폰 체크
	var pattern1 =  /^\d{2,3}-\d{3,4}-\d{4}$/;
	var pattern2 =  /^\d{4}-\d{4}$/;
	if(pattern1.test(phone) || pattern2.test(phone)){
		//alert(text+"는  (00~000)-0000-0000으로 입력해주세요");
		return true;
	}else{
		return false;
	}
}

function checkNumEng(text, minLength, maxLength){
	//text(문자열), minLength(최소자리수) maxLength(최대자리수)
	//영소문자 숫자 조합
	var len = text.length;
	if(len >= minLength && len <= maxLength){
		var pattern = /^[A-Za-z0-9\s]+$/;
		if(!pattern.test(text)){
			//alert(temp+"는 영대소문자 숫자 조합으로 입력해주세요.");
			return false;
		}else{
			return true;
		}
	}else{
		//alert(temp+"은 "+minLength+"자리 이상 "+maxLength+"자리 이하로 입력해주세요.");
	}
}

function checkDateFormat(text){
	//text(문자열)
	//yyyy-dd-mm 체크
	var pattern = /^\d{4}-\d{2}-\d{2}$/;
	if(!pattern.test(text)){
		alert("날짜 패턴 실패");
	}else{
		alert("날짜 패턴 성공");
	}
}

function checkNumFormat(num){
	//num(숫자)
	//숫자인지 체크
	var pattern = /^[0-9]+$/;
	if(pattern.test(num)){
		//천단위 , 찍기
		pattern = /(^[+-]?\d+)(\d{3})/; 
		num += '';                        
		while (pattern.test(num))
			num = num.replace(pattern, '$1' + ',' + '$2');
		//alert(num+" 성공");
		return true;
	}else{
		//alert("숫자만 입력이 가능 합니다.");
		return false;
	}
}

function checkNumFormatText(num, text){
	//num(숫자)
	//숫자인지 체크
	var pattern = /^[0-9]+$/;
	if(pattern.test(num)){
		//천단위 , 찍기
		pattern = /(^[+-]?\d+)(\d{3})/; 
		num += '';                        
		while (pattern.test(num))
			num = num.replace(pattern, '$1' + ',' + '$2');
		//alert(num+" 성공");
		return true;
	}else{
		alert(text+"는(은) 숫자만 입력이 가능 합니다.");
		return false;
	}
}

function checkNum(temp, num){
	//숫자인지 체크
	var pattern = /^[0-9]+$/;
	if(pattern.test(num)){
		return true;
	}else{
		alert(temp+"는 숫자만 입력이 가능 합니다.");
		return false;
	}
	
}

//두개의 문자열 일치여부
function compareText(fText, sText){
	if(fText != sText){
		//alert(text+"가 일치하지 않습니다. 다시 입력해주세요.");
		return false;
	}
	return true;
}

//문자열 널값 체크
function checkNull(checkText){
	if(checkText == ""){
		return false;
	}
	return true;
}

//날짜 비교
function compareDate(fiducialDate, readingDate){
	
	var start = fiducialDate.split(" ");
	var startDate = start[0];
	var startTime = start[1];
	
	var startDates = startDate.split("-");
	var startTimes = startTime.split(":");
	
	var end = readingDate.split(" ");
	var endDate = end[0];
	var endTime = end[1];
	
	var endDates = endDate.split("-");
	var endTimes = endTime.split(":");
	
  	var sDate = new Date(startDates[0],startDates[1],startDates[2],startTimes[0],startTimes[1],"00").valueOf();
  	var eDate = new Date(endDates[0],endDates[1],endDates[2],endTimes[0],endTimes[1],"00").valueOf();
  	
  	if(sDate > eDate){
		return false;
	}
  	return true;
}

//사업자등록번호 체크
function checkBizID(bizID){
	var pattern =  /^\d{3}-\d{2}-\d{5}$/;
	if(!pattern.test(bizID)){
		alert("사업자 등록번호가 잘못 입력되었습니다.(000-00-00000)\n다시 입력해주세요.");
		return false;
	}else{
		return true;
	}
}

//URL 주소 체크
function checkUrl(urls) {
var chkExp = /http:\/\/([\w\-]+\.)+/g;
	if (chkExp.test(urls)) {
		return true;
	} else {
		return false;
	}
}

