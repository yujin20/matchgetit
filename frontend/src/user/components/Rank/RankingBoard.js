import React, {useEffect, useState} from 'react';
import '../../styles/RankingBoard.css';
import axios from "axios";


function RankingBoard ({session}){
    const [activeTab, setActiveTab] = useState(0);
    const [data, setData] = useState([]);
    const [selectedGrade, setSelectedGrade] = useState("ALL");

    const gradeComp = (rating2) => {
        let grade = "";
        let rating3 = parseInt(rating2);
        if(rating3>=1001) {
            grade = "E";
        } else if (rating3>=851){
            grade = "D";
        } else if (rating3>=501){
            grade = "C";
        } else if (rating3>=201){
            grade = "B";
        } else {
            grade = "A";
        }
        return grade;
    };

    const VicRatingComf = (win,lose) => {
        let win2 = parseInt(win);
        let lose2 = parseInt(lose);
        let VicRating = win2*100/(lose2+win2)+"%";
        return VicRating;
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('/matchGetIt/rank');
                setData(response.data);
            } catch (error) {
                console.error('Error:', error);
            }
        };

        console.log(data);
        fetchData();
    }, []);


    //레벨별 랭킹보기
    useEffect(() => {
        const tabMenu = document.querySelector('.tab-menu');
        const tabs = tabMenu.querySelectorAll('li');

        const showTabContent = (index) => {
            const tabContents = tabMenu.querySelectorAll('.rank_table');
            tabContents.forEach((content, i) => {
                if (i === index) {
                    content.style.display = 'table';
                } else {
                    content.style.display = 'none';
                }
            });
        };
        tabs.forEach((tab, index) => {
            tab.addEventListener('click', (e) => {
                e.preventDefault();
                setActiveTab(index);
                showTabContent(index);
            });
        });
        showTabContent(activeTab);
    }, [activeTab]);

    return (
        <div className="matchBoard_outline">
            <div className="boardName">
                <h6>Ranking Board</h6>
            </div>
            <div className="please">
            <div className="rank_table_wrap">
                <div className="ranking_menu">
                <ul>
                    <li className={selectedGrade === "ALL" ? 'active' : ''}>
                        <a href="#" onClick={() => setSelectedGrade("ALL")} className="Ranking_ALL">ALL</a>
                    </li>
                    <li className={selectedGrade === "A" ? 'active' : ''}>
                        <a href="#" onClick={() => setSelectedGrade("A")} className="Ranking_A">A</a>
                    </li>
                    <li className={selectedGrade === "B" ? 'active' : ''}>
                        <a href="#" onClick={() => setSelectedGrade("B")} className="Ranking_B">B</a>
                    </li>
                    <li className={selectedGrade === "C" ? 'active' : ''}>
                        <a href="#" onClick={() => setSelectedGrade("C")} className="Ranking_C">C</a>
                    </li>
                    <li className={selectedGrade === "D" ? 'active' : ''}>
                        <a href="#" onClick={() => setSelectedGrade("D")} className="Ranking_D">D</a>
                    </li>
                </ul>
                </div>
                <table className="rank_myRank">
                    <caption></caption>
                    <thead>
                        <tr className="rank_top">
                            <th>등급</th>
                            <th>순위</th>
                            <th>이름</th>
                            <th>포인트</th>
                            <th>승률</th>
                            <th>최근 10경기</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr className="rank01" >
                            <td>
                                <p className="ranking_grade">
                                    {gradeComp(session.rating)}
                                </p>
                            </td>
                            <td></td>
                            <td>{session.name}</td>
                            <td>{session.rating}</td>
                            <td>{VicRatingComf(session.win,session.lose)}</td>
                            <td>{session.win}승 {session.lose}패</td>
                        </tr>
                    </tbody>
                </table>

                <div className="tab-menu">

                        <table className="rank_table" >
                            <caption></caption>
                            <thead className="rank_topBar">
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            {data.filter(item => selectedGrade === "ALL" || item.groupId === selectedGrade)
                                .map((item) => (
                            <tbody className="rank_tbody">
                                <tr className="rank01" key={item.rankId}>
                                    <td>
                                        <p className="ranking_grade">
                                            {item.groupId}
                                        </p>
                                    </td>
                                    <td>{item.rank}</td>
                                    <td>{item.name}</td>
                                    <td>{item.rating}</td>
                                    <td>{item.vicRating}</td>
                                    <td>{item.win}승 {item.lose}패</td>
                                </tr>
                            </tbody>
                            ))}
                        </table>


                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                                <tr>
                                    <th>등급</th>
                                    <th>순위</th>
                                    <th>이름</th>
                                    <th>포인트</th>
                                    <th>승률</th>
                                    <th>최근 10경기</th>
                                </tr>
                            </thead>
                            {data.filter(item => selectedGrade === "ALL" || item.groupId === selectedGrade)
                                .map((item) => (
                                <tbody className="rank_tbody">
                                <tr className="rank01" key={item.rankId}>
                                    <td>
                                        <p className="ranking_grade">
                                            {item.groupId}
                                        </p>
                                    </td>
                                    <td>{item.rankId}</td>
                                    <td>{item.name}</td>
                                    <td>{item.rating}</td>
                                    <td>{item.vicRating}</td>
                                    <td>{item.win}승 {item.lose}패</td>
                                </tr>
                                </tbody>
                            ))}
                        </table>

                        <table className="rank_table" style={{ display: activeTab === 0 ? 'table' : 'none' }}>
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            {data.filter(item => selectedGrade === "ALL" || item.groupId === selectedGrade)
                                .map((item) => (
                                <tbody className="rank_tbody">
                                <tr className="rank01" key={item.rankId}>
                                    <td>
                                        <p className="ranking_grade">
                                            {item.groupId}
                                        </p>
                                    </td>
                                    <td>{item.rankId}</td>
                                    <td>{item.name}</td>
                                    <td>{item.rating}</td>
                                    <td>{item.vicRating}</td>
                                    <td>{item.win}승 {item.lose}패</td>
                                </tr>
                                </tbody>
                            ))}
                        </table>

                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            {data.filter(item => selectedGrade === "ALL" || item.groupId === selectedGrade)
                                .map((item) => (
                                <tbody className="rank_tbody">
                                <tr className="rank01" key={item.rankId}>
                                    <td>
                                        <p className="ranking_grade">
                                            {item.groupId}
                                        </p>
                                    </td>
                                    <td>{item.rankId}</td>
                                    <td>{item.name}</td>
                                    <td>{item.rating}</td>
                                    <td>{item.vicRating}</td>
                                    <td>{item.win}승 {item.lose}패</td>
                                </tr>
                                </tbody>
                            ))}
                        </table>

                        <table className="rank_table">
                            <caption></caption>
                            <thead>
                            <tr>
                                <th>등급</th>
                                <th>순위</th>
                                <th>이름</th>
                                <th>포인트</th>
                                <th>승률</th>
                                <th>최근 10경기</th>
                            </tr>
                            </thead>
                            {data.filter(item => selectedGrade === "ALL" || item.groupId === selectedGrade)
                                .map((item) => (
                                <tbody className="rank_tbody">
                                <tr className="rank01" key={item.rankId}>
                                    <td>
                                        <p className="ranking_grade">
                                            {item.groupId}
                                        </p>
                                    </td>
                                    <td>{item.rankId}</td>
                                    <td>{item.name}</td>
                                    <td>{item.rating}</td>
                                    <td>{item.vicRating}</td>
                                    <td>{item.win}승 {item.lose}패</td>
                                </tr>
                                </tbody>
                            ))}
                        </table>
                </div>
            </div>
            </div>
        </div>
    );
};

export default RankingBoard;