import React, { useState } from 'react';
import { Table } from 'antd';
import { useEffect } from 'react';
import Container from '../../components/Container';
import PaymentService from '../../services/PaymentService';

export default function PaymentList() {

    const [Payments, setPayments] = useState([]);

    useEffect(() => {
        PaymentService.getTransactions()
            .then((res) => {
                setPayments(res.data.content);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [])

    if (Payments && Payments.length) {
        const columns = [
            {
                title: 'Payment Id',
                dataIndex: 'id',
                key: 'id',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.id - b.id,
            },
            {
                title: 'Transaction Id',
                dataIndex: 'transactionId',
                key: 'transactionId',
                defaultSortOrder: 'ascend',
                sorter: (a, b) => a.transactionId - b.transactionId,
            },
            {
                title: 'Student Name',
                dataIndex: 'studentName',
                key: 'studentName',
                sorter: (a, b) => a.studentName.length - b.studentName.length,
                sortDirections: ['ascend'],
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
                title: 'Transaction Status',
                dataIndex: 'transactionStatus',
                key: 'transactionStatus',
                sorter: (a, b) => a.transactionStatus.length - b.transactionStatus.length,
                sortDirections: ['ascend'],
            },
            {
                title: 'Transaction Date',
                dataIndex: 'transactionDate',
                key: 'transactionDate',
                sorter: (a, b) => new Date(a.date) - new Date(b.date),
                sortDirections: ['ascend'],
            }
        ];

        return (
            <Container>
                <h3 className='text-center m-2 p-2'>Payments History</h3>
                <Table
                    dataSource={Payments}
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
                <h3>Payments History Not Available</h3>
            </div>
        </Container>
    )
}