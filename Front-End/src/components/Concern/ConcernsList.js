import React, { useState, useEffect } from 'react';
import { Table } from 'antd';
import Container from '../Container';
import ConcernService from '../../services/ConcernService';

export default function ConcernsList() {

    const [Concerns, setConcerns] = useState([]);   

    useEffect(() => {
        ConcernService.getAllConcernsForSingleUser()
            .then((res) => {
                setConcerns(res.data)
            })
            .catch((err) => {
             console.log(err)
            })
    }, [])

    if (Concerns && Concerns.length) {
        const columns = [
            {
                title: 'Complaint Id',
                dataIndex: 'id',
                key: 'id',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.id - b.id,
            },
            {
                title: 'Hostel Name',
                dataIndex: 'hostelName',
                key: 'hostelName',
                sorter: (a, b) => a.hostelName.length - b.hostelName.length,
                sortDirections: ['ascend'],
            },
            {
                title: 'Room No',
                dataIndex: 'roomNo',
                key: 'roomNo',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.roomNo - b.roomNo,
            },
            {
                title: 'Student Name',
                dataIndex: 'studentName',
                key: 'studentName',
                sorter: (a, b) => a.studentName.length - b.studentName.length,
                sortDirections: ['ascend'],
            },
            {
                title: 'Subject',
                dataIndex: 'subject',
                key: 'subject',
                sorter: (a, b) => a.subject.length - b.subject.length,
                sortDirections: ['ascend'],
            },
            {
                title: 'Concern',
                dataIndex: 'message',
                key: 'message',
                sorter: (a, b) => a.message.length - b.message.length,
                sortDirections: ['ascend'],
            }
        ];

        return (
            <Container>
                <h3 className='text-center m-2 p-2'>Concerns List</h3>
                <Table
                    dataSource={Concerns}
                    columns={columns}
                    rowKey='id'
                    pagination={{defaultPageSize:8}}
                />
            </Container>
        )
    }
    return (
        <Container>
            <div className='text-center'>
                <h3>Concerns Not Available</h3>
            </div>
        </Container>
    )
}