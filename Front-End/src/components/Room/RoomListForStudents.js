import React, { useState } from 'react';
import { Table, Space, Button } from 'antd';
import Container from '../Container';
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from 'react';
import RoomService from '../../services/RoomService';


export default function RoomListForStudents() {

    const [Rooms, setRooms] = useState([]);

    useEffect(() => {
        RoomService.displayRooms()
            .then((res) => {
                setRooms(res.data.content)
            })
            .catch((error) => {
                console.log(error)
            });
    }, [])

    let navigate = useNavigate();
    const HostelId = useLocation().state;


    if (Rooms && Rooms.length) {
        const columns = [
            {
                title: 'Room Id',
                dataIndex: 'id',
                key: 'id',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.id - b.id,
            },
            {
                title: 'Hostel Id',
                dataIndex: 'hostelId',
                key: 'hostelId',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.id - b.id,
            },
            {
                title: 'Hostel Name',
                dataIndex: 'hostelName',
                key: 'hostelName',
                sorter: (a, b) => a.name.length - b.name.length,
                sortDirections: ['ascend'],
            },
            {
                title: 'Room No',
                dataIndex: 'roomNo',
                key: 'roomNo',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.contactMobileNo - b.contactMobileNo,
            },
            {
                title: 'Vacant',
                dataIndex: 'isVacant',
                key: 'isVacant',
            },
            {
                title: 'Action',
                key: 'action',
                render: (d) =>
                    <Space size="middle">
                        <Button disabled={d.isVacant === 'Vacant' ? false : true} onClick={(e) => {
                            console.log("column click", d.id)
                            sessionStorage.setItem("hosteldetail", JSON.stringify(d))
                            navigate('/student/payment')
                        }}>
                            Book</Button>
                    </Space>
            }
        ];

        return (
            <Container>
                <h3 className='text-center m-2 p-2'>Rooms List</h3>
                <Table
                    dataSource={Rooms}
                    columns={columns}
                    rowKey='id'
                    pagination={{ defaultPageSize: 8 }}
                    size='middle'
                />
            </Container>
        )
    }
    return (
        <Container>
            <div className='text-center'>
                <h3>Rooms Not Available</h3>
            </div>
        </Container>
    )
}