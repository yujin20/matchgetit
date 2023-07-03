import React from 'react';
import '../../styles/MatchingPage/Wait/waitingPage.css';
import axiosInstance from "../axiosInstance";

const MatchResult = ({ session, matchWaitData, setIsMatch, setMatchWaitData, party }) => {
    if (matchWaitData === null) {
        return (
            <>잘못된 접근입니다.</>
        );
    }

    // applicationTime에 따라 한글로 표현
    let applicationTimeText = '';
    switch (matchWaitData[0].party?.applicationTime) {
        case 'A':
            applicationTimeText = '오전 10시 ~ 오후 12시';
            break;
        case 'B':
            applicationTimeText = '오후 12시 ~ 오후 2시';
            break;
        case 'C':
            applicationTimeText = '오후 2시 ~ 오후 4시';
            break;
        case 'D':
            applicationTimeText = '오후 4시 ~ 오후 6시';
            break;
        case 'E':
            applicationTimeText = '오후 6시 ~ 오후 8시';
            break;
        case 'F':
            applicationTimeText = '오후 8시 ~ 오후 10시';
            break;
        default:
            applicationTimeText = '';
            break;
    }

    const cancelMatch = () => {
        axiosInstance.post("/matchGetIt/match/cancelMatchWait", null, { params: { id: session.userId } })
            .then(response => {
                console.log('취소 성공');
            }).catch(error => {
            console.log('취소 실패');
        });
    };

    const renderTeamMembers = (team) => {
        const members = matchWaitData.filter(m => m.team===team);
        return members.map((item, index) => (
            <li key={index}>
                <div className="MatchTeamMember">{item.member.name}</div>
            </li>
        ));
    };

    return (
        <div className="resultContainer">
            <div className="resultStatiumimg">
                {/*<img src={matchWaitData.stadium.stdImgUrl} alt="Stadium" />*/}
            </div>
            <div className="resultTime">{applicationTimeText}</div>
            <div className="resultStadium">매칭구장: {matchWaitData[0].stadium.stdName}</div>
            <div className="lineUp">LINE_UP</div>
            <div className="aTeam">
                <div className="uniform">
                    <img src="public/images/uniformA.png" alt="Ateam" />
                </div>
                <div className="resultMember">
                    <ul>{renderTeamMembers("A")}</ul>
                </div>
            </div>
            <div className="bTeam">
                <div className="uniform">
                    <img src="public/images/uniformB.png" alt="Bteam" />
                </div>
                <div className="resultMember">
                    <ul>{renderTeamMembers("B")}</ul>
                </div>
                <button type="button" onClick={cancelMatch}>경기 취소</button>
            </div>
        </div>
    );
};

export default MatchResult;
