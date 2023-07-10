import React, {useEffect, useState} from 'react';
import '../Payments/CreditHistory.css';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import moment from "moment";
import axiosInstance from "../../components/axiosInstance";
import axios from "axios";


function CreditHistory ({session}){

    const [value, onChange] = useState(new Date());
    const [data, setData] = useState([]);
    const [marks, setMarks] = useState([]);
    const [selectedDateContent, setSelectedDateContent] = useState([]);
    const [paymentData, setPaymentData] = useState([]);

    const handleDateClick = (date) => {
        const selectedDate = moment(date).format('YYYY-MM-DD');
        console.log(session.userId);

        axios.post('/matchGetIt/payment/userPayHistory',null, {params:{userId : session.userId, date : selectedDate}})
            .then(response => {
                    console.log(response.data);
                    setSelectedDateContent(response.data)
                }
            )
            .catch(error => {
                console.error('에러:', error);
            });
    };




    const findContentByDate = (date) => {
        return data.filter((item) => item.transactionDateTime === date);
    };

    const tileClassName= ({ date, view }) => {
        if (marks.find((x) => x === moment(date).format("YYYY-MM-DD"))) {
            return 'highlight';
        }
        return '';
    };


    // useEffect(() => {
    //     const fetchData = async () => {
    //         try {
    //             const response = await axios.get('/matchGetIt/payment/userList');
    //             setData(response.data);
    //             const dates = response.data.map((item) => item.date).filter(item => session.userId === item.userId);
    //             setMarks(dates);
    //             console.log(data);
    //         } catch (error) {
    //             console.error('Error:', error);
    //         }
    //     };
    //     fetchData();
    //     console.log(data);
    // }, []);

    // useEffect(() => {
    //
    //     const fetchPaymentData = async () => {
    //         try {
    //             const data = await getPaymentList();
    //             setPaymentData(data);
    //         } catch (error) {
    //             console.error('에러:', error);
    //         }
    //     };
    //
    //     fetchPaymentData();
    //     console.log(data);
    // }, []);
    //
    //
    // const getPaymentList = () => {
    //     return axios.get("/matchGetIt/payment/paymentList")
    //         .then(response => response.data)
    //
    //         .catch(error => {
    //             console.error('에러:', error);
    //         });
    // };
    //




    return (
        <div>
            <div className="basicDiv">
                <Calendar
                    onChange={onChange}
                    value={value}
                    locale="en-EN"
                    className="calendar"
                    tileClassName={tileClassName}
                    onClickDay={handleDateClick} // 날짜를 클릭했을 때 호출될 함수
                /><hr />
                {selectedDateContent.length > 0 && (
                    <>
                        {selectedDateContent.map((content, index) => (
                            <div className="CreditHistory" key={index}>
                                <p>{(content.transactionDate)}</p>
                                {content.price>0?(<p>포인트 충전</p>):(<p>매칭 결제</p>)}
                                <h3>{content.price}원</h3>
                            </div>
                        ))}
                    </>
                )}
            </div>
        </div>
    );
}

export default CreditHistory;