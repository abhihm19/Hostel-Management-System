import React, { useState } from 'react';
import { Table } from 'antd';
import Container from '../Container';
import { useEffect } from 'react';
import StudentService from '../../services/StudentService';

export default function StudentsList() {

    const [students, setStudents] = useState([]);

    useEffect(() => {
        StudentService.getAllStudents.then((res) => {
            setStudents(res.data.content)
        })
        .catch((error) => {
            console.log(error)
        });
    }, [])

    if (students && students.length) {
        const columns = [
            {
                title: 'Hostel Name',
                dataIndex: 'hostelName',
                key: 'hostelName',
                sorter: (a, b) => a.hostelName.length - b.hostelName.length,
                sortDirections: ['descend'],
            },
            {
                title: 'Room No',
                dataIndex: 'roomNo',
                key: 'roomNo',
                defaultSortOrder: 'descend',
                sorter: (a, b) => a.roomNo - b.roomNo,
            },
            {
                title: 'First Name',
                dataIndex: 'firstName',
                key: 'firstName',
                sorter: (a, b) => a.firstName.length - b.firstName.length,
                sortDirections: ['descend'],
            },
            {
                title: 'Last Name',
                dataIndex: 'lastName',
                key: 'lastName',
                sorter: (a, b) => a.firstName.length - b.firstName.length,
                sortDirections: ['descend'],
            },
            {
                title: 'Gender',
                dataIndex: 'gender',
                key: 'gender',
            },
            {
                title: 'Mobile No',
                dataIndex: 'mobileNo',
                key: 'mobileNo',
            },
            {
                title: 'Course',
                dataIndex: 'course',
                key: 'course',
            },
            {
                title: 'Email Id',
                dataIndex: 'emailId',
                key: 'emailId',
            }            
        ];

        return (
            <Container>
                <h3 className='text-center m-2 p-2'>Students List</h3>
                <Table
                    dataSource={students}
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
                <h3>Students Not Available</h3>
            </div>
        </Container>
    )
}