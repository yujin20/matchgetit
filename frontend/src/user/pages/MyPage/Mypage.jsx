import React, {useState} from "react";
import "./Mypage.css";
import "./profile.css";
import "../Payments/CreditCharge.css";
import Profile from "./Profile";
import CreditHistory from "../Payments/CreditHistory";
import CreditCharge from "../Payments/CreditCharge";
import ApplyManager from "../ManagerRecruitment/ApplyManager";
import axiosInstance from "../../components/axiosInstance";
import QABoard from "../QABoard/QABoard";
import ManagerMatchPage from "../ManagerPage/ManagerMatchPage";
import '../../styles/ManagerPage/ManagerGameMg.css';
import '../../styles/CommonFactor/grade.css';



function Mypage({session}) {
    const [isProfileOpen, setProfileOpen] = useState(false);
    const [isChargeOpen, setChargeOpen] = useState(true);
    const [isAdmin, setIsAdmin] = useState(session && session.loginType.toUpperCase() === "ADMIN");
    console.log("session:", session)
    console.log(session.loginType)
    console.log(isAdmin)
    // const [isAdmin, setIsAdmin] = useState(false);
    const [isMng, setIsMng] = useState(true);

    //임의로 매니저 true로 해놨음 원래로직대로면 매니저신청 >> 관리자 등록 >> role체크 후 true로 바뀌어야 함
    const [isApplyManager, setApplyManager] = useState(false);
    const [isHistoryOpen, setHistoryOpen] = useState(false);
    const [isQABoardOpen, setQABoardOpen] = useState(false);
    const [isMngMenuOpen, setIsMngMenuOpen] = useState(false);

    const handleApplyManagerToggle = () => {
        setApplyManager(!isApplyManager);
    };

    const handleProfileToggle = () => {
        setProfileOpen(!isProfileOpen);
    };
    const handleChargeToggle = () => {
        setChargeOpen(!isChargeOpen);
    };

    const handleHistoryToggle = () => {
        setHistoryOpen(!isHistoryOpen);
    };
    const handleQABoardToggle = () => {
        setQABoardOpen(!isQABoardOpen);
    };

    const handleMngMenuToggle = () => {
        setIsMngMenuOpen(!isMngMenuOpen);
    };

    const getRole = () => {
        axiosInstance.post("matchGetIt/auth/getRole")
            .then(res=>{
                if(res.data=='manager')setIsMng(true);
                else if(res.data=='admin')setIsAdmin(true);
                else{
                    setIsMng(false);
                    setIsAdmin(false);
                }
            }).catch(err=>{
            console.log('서버 오류');
        })//해당 유저의 권한을 가져오는 함수
    }

    return (
        <div className="content-body_left-wrap">
            <section>
                <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div className="my-profile">
                        <div style={{ display: 'flex', alignItems: 'center' }}>
                            <h1 className="my-profile__name">{session.name}</h1>
                        </div>
                        <div className="my-account_type">
                            <p className="text-caption1">{session.userId}</p>
                            <span className="badge badge-kakao sm">{session.accountType}</span>
                        </div>
                    </div>
                </div>
                <div style={{ marginTop: '10px', display: 'none' }}>
                    <p className="my-profile__notice--friend">
                        <a href="/player/17AcO/friend/?tab=pending">0명의 친구 신청이 있어요</a>
                    </p>
                </div>
                <div className="my-status">
                    <li className="my-status_item my-status_item-double">
                        {/*<a href="/mypage/mymanner/">*/}
                            <div className="my-status_label">
                                <p style={{ fontSize: '12px' }}>매너</p>
                            </div>
                            <div className="my-status_content">
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_manner_card.svg" alt="매너" />
                                좋아요
                            </div>
                        {/*</a>*/}
                    </li>
                    <li className="my-status_item my-status_item-double">
                        <div className="my-status_label">
                            <a href="/magazine/1/" style={{ fontSize: '12px' }}>숙련도</a>
                            <img src="https://plab-football.s3.amazonaws.com/static/img/ic_level_show.svg" alt="레벨" />
                        </div>
                        {/*<a href="/mypage/mylevel/">*/}
                            <div className="my-status_content">
                                <div className="lv-system_title">
                                    <div>
                                        <span className="badge badge-rookie badge_lv-system">R</span>
                                    </div>
                                    <div>
                                        <div className="lv-system_name">
                                            <h4>루키</h4>
                                        </div>
                                    </div>
                                    <div>
                                        {/* Additional content */}
                                    </div>
                                </div>
                            </div>
                        {/*</a>*/}
                    </li>
                </div>
                {isChargeOpen && (
                    <li className="my-status_item my-status_item-cash">
                        <div>
                            <p style={{ fontSize: '17px', fontWeight: "bold"}}>나의 캐시</p>
                            <p style={{ fontSize: '20px', fontWeight: '700' }}>{session.ownedCrd!=null?session.ownedCrd:0}원</p>
                        </div>

                        <div className="my-cash">
                            <span onClick={handleChargeToggle}>충전하기</span>
                        </div>
                    </li>
                )}
                {!isChargeOpen && (
                    <li className="my-status CreditCharge-settings">
                        <div className="CreditCharge-settings">
                            <CreditCharge session={session}/>
                            <div className="charge__back" onClick={handleChargeToggle}>
                                닫기
                            </div>
                        </div>
                    </li>
                )}

                <div className="profile-view">
                    <button className="btn sm gray" onClick={handleProfileToggle}>
                        <p>프로필 보기</p>
                    </button>
                </div>
                {isProfileOpen && (
                    <div className="profile-settings">
                        <Profile session={session}/>
                    </div>
                )}
                {/*<div className="profile-view">*/}
                {/*    <button className="btn sm gray" onClick={handleApplyManagerToggle}>*/}
                {/*        <p>매니저 지원하기</p>*/}
                {/*    </button>*/}
                {/*</div>*/}
                {/*{isApplyManager && (*/}
                {/*    <div className="ApplyManager">*/}
                {/*        <ApplyManager session={session}/>*/}
                {/*    </div>*/}
                {/*)}*/}

            </section>
            <section>
                <div>
                    <div className="my-content-title">my content</div>
                        <div className="content-label">
                            <img src="https://plab-football.s3.amazonaws.com/static/img/ic_myplab_color.svg" alt="신청 내역"/>
                            <p>신청 내역</p>
                        </div>
                        <div>
                            <a className="content-label" onClick={handleHistoryToggle}>
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_point_color.svg" alt="결제 내역"/>
                                <p>결제 내역</p>
                            </a>
                            {isHistoryOpen && (
                                <div className="profile-settings">
                                    <CreditHistory session={session}/>
                                </div>
                            )}
                        </div>
                        <div>
                            <a className="content-label" onClick={handleQABoardToggle}>
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_faq_color.svg" alt="문의 게시판" />
                                <p>문의 게시판</p>
                            </a>
                            {isQABoardOpen && (
                                <div className="profile-settings">
                                    <QABoard session={session}/>
                                </div>
                            )}
                        </div>
                    {!isMng && (
                        <div>
                            <a className="content-label" onClick={handleApplyManagerToggle}>
                                <img src="https://plab-football.s3.amazonaws.com/static/img/explore_fire.svg" alt="매니저 지원"/>
                                <p>매니저 지원하기</p>
                            </a>
                            {isApplyManager && (
                                <div className="ApplyManager">
                                    <ApplyManager session={session}/>
                                </div>
                            )}
                        </div>
                    )}
                    {isAdmin && (
                        <a href="http://localhost:8081/matchGetIt/admin/gate">
                            <div className="content-label">
                                <img src="https://plab-football.s3.amazonaws.com/static/img/ic_myplab_color.svg" alt="어드민 페이지" />
                                <p>어드민 페이지 이동</p>
                            </div>
                        </a>
                    )}
                    {isMng && (
                        <>
                            <div className="content-label mngMenu" onClick={handleMngMenuToggle}>
                                <p>매니저 페이지 이동</p>
                            </div>
                            {isMngMenuOpen && (
                                <ManagerMatchPage session={session} isMngOpen={isMngMenuOpen} handleMngMenuToggle={handleMngMenuToggle}/>
                            )}
                        </>
                        )}
                </div>
            </section>
        </div>
    );
}


export default Mypage;