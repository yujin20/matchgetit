import './css/QARegisterForm.css'
function QARegisterForm({session}) {
        const userId = session.userId;
    return (
        <div>
            <div className="QAIn-wrap">
                <div className="QAIn-box">
                    <div className="QAInContainer">
                        <div className="QAInForm">
                            <form action="/matchGetIt/userInquiry" method="post">
                                <select id="category" className="category" name="category">
                                    <option value="계정">계정</option>
                                    <option value="결제">결제</option>
                                    <option value="경기/매칭">경기/매칭</option>
                                    <option value="기타">기타</option>
                                </select>
                                <br />
                                <input type="text" className="QAName" name="QAName" hidden value={session.userId} />
                                <input type="text" className="QAtitle" name="QAtitle" placeholder="제목을 입력해주세요" required/><br />
                                <input type="text" className="QAcontent" name="QAcontent" placeholder="내용을 입력해주세요"  required /><br />
                                <button className="submitBtn" type="submit">
                                    등록하기
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default QARegisterForm;