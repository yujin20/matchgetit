import React, { useState,useEffect } from 'react';
import '../../styles/MatchingPage/Wait/waitingPage.css';
import axiosInstance from "../axiosInstance";

const MatchWait = ({ session, party,setParty,setIsParty,findMatch }) => {
    const [matchStatus, setMatchStatus] = useState('');
    const [partyData, setPartyData] = useState(null);
    const [matchAgreeData, setMatchAgreeData]= useState(null);
    const [matchAccept, setMatchAccept] = useState(false); // 추가된 부분
    const [agreeVisible, setAgreeVisible] = useState(true);
    useEffect(() => {
        findPartyData();
        findMatch();

        const interval = setInterval(() => {
            findMatch();
            handleRenew();
        }, 5000);

        return () => clearInterval(interval);
    }, []);

    const findPartyData = () => {
        axiosInstance
            .post('/matchGetIt/match/getParty', null, { params: { id: session.userId } })
            .then((response) => {
                if (response.data.length !== 0) {
                    console.log(response.data);
                    setPartyData(response.data);
                }
            })
            .catch((error) => {
                console.log('파티 없음 또는 서버오류');
            });
    }

    const handleRenew = () => {
        axiosInstance
            .post("/matchGetIt/match/renewMatchList", null, { params: { id: session.userId } })
            .then((response) => {
                console.log('데이터 옴');
                console.log(';');
                console.log(response.data);
                if(response.data.length>=1){
                    setMatchAgreeData(response.data);
                    setMatchAccept(true);
                }else{
                    setMatchAccept(false);
                }
            })
            .catch((error) => {
                console.log('실패');
            });
    };
    const handleAccept = () => {
        axiosInstance.post('/matchGetIt/match/matchAccept',null,{params:{id:session.userId}})
            .then(
                response=>{
                    console.log(response.data);
                    handleRenew();
                    setAgreeVisible(false);
                }
            ).catch(error=>{

        });
    };

    const handleReject = () => {
        axiosInstance.post('/matchGetIt/match/matchReject',null,{params:{id:session.userId}})
            .then(
                response=>{
                    console.log(response.data);
                    cancel();
                    handleRenew();
                }
            ).catch(error=>{
        });
    };


    if (party === null) {
        return (
            <>잘못된 접근입니다.</>
        );
    }

    // applicationTime에 따라 한글로 표현
    let applicationTimeText = '';
    switch (party?.applicationTime) {
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
    const cancel=()=>{
        axiosInstance.post("/matchGetIt/match/cancel",null,{
            params:{
                id:session.userId
            }
        }).then(response=>{
            // setMatchStatus('취소 성공');
            setMatchStatus('');
            setParty(null);
            setIsParty(false);
        }).catch(error=>{
            setMatchStatus('취소 실패');
            setTimeout(() => {
                setMatchStatus('');
            }, 3000);
        });
    }

    return (
        <div className="waitContainer">
            {!matchAccept ? (
                    <div className="waitTitle">
                        {session.name} 님 매칭 중
                    </div>)
                :(<div className="waitTitle">
                    {session.name} 수락 요청 도착
                    <br/> 다른 인원 수락 대기중 ({matchAgreeData.filter(m=>m.accept=='AGREE').length}/2)
                </div>)
            }
            <div className="waitAddress">{partyData?.address}</div>
            <div className="waitTime">{applicationTimeText}</div>
            <div className="Team">Team</div>
            <ul>
                {party.map((item, index) => (
                    <li key={index}>
                        <div className="TeamMember">{item.name}</div>
                    </li>
                ))}
            </ul>
            <p>{matchStatus}</p>
            {!matchAccept? (
                <button type="button" className="glassBtn" onClick={cancel}>
                    매칭 취소
                </button>
            ) : (
                agreeVisible && (
                    <div>
                        <button type="button" className="glassBtn"  onClick={handleAccept}/*수락 메소드 기입*/>
                            수락
                        </button>
                        <button type="button" className="glassBtn" onClick={handleReject}/*거절 메소드 기입*/>
                            거절
                        </button>
                    </div>
                )
            )}
        </div>
    );
};

export default MatchWait;