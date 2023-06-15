import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import StudentDashboard from '../components/Student/StudentDashboard';
import StudentRegister from '../components/Student/StudentRegister';
import StudentProfile from '../components/Student/StudentProfile';
import SharedStudentLayout from '../components/Student/SharedStudentLayout';
import HostelsList from '../components/Hostel/SelectHostel';
import RoomListForStudents from '../components/Room/RoomListForStudents';
import Payments from '../components/Payment/PaymentList';
import AllotmentStatus from '../components/Room/AllotmentStatus';
import AddConcern from '../components/Concern/AddConcern';
import StudentUpdate from '../components/Student/StudentUpdate';
import Logout from '../components/Home/Logout';
import RoomStatus from '../components/Room/RoomStatus';


class StudentRouter extends Component {
    
    render() {
        return (
            <div>
                <Router>
                    <Routes>                        
                        <Route exact path="student" element={<SharedStudentLayout />}>
                            <Route index element={<StudentDashboard />} />
                            <Route path="register" element={<StudentRegister />} />
                            <Route path="hostel" element={<HostelsList />} />
                            <Route path="hostel/room" element={<RoomListForStudents />} />
                            <Route path="payment" element={<Payments />} />
                            <Route path="status" element={<AllotmentStatus />} />
                            <Route path="concern" element={<AddConcern />} />
                            <Route path="profile" element={<StudentProfile />} />
                            <Route path="update" element={<StudentUpdate />} />
                            <Route path="roomstatus" element={<RoomStatus />} />
                            <Route path="logout" element={<Logout />} />
                        </Route>    
                    </Routes>
                </Router>
            </div>
        );
    }
}

export default StudentRouter;