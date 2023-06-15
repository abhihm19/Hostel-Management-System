import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import StudentsList from '../components/Student/StudentsList';
import EmployeeDashboard from '../components/Employee/EmployeeDashboard';
import HostelsList from '../components/Hostel/HostelsList';
import ConcernsList from '../components/Concern/ConcernsList';
import EmployeeProfile from '../components/Employee/EmployeeProfile';
import SharedEmployeeLayout from '../components/Employee/SharedEmployeeLayout';
import AddHostel from '../components/Hostel/AddHostel';
import AddRooms from '../components/Room/AddRooms';
import UpdateHostel from '../components/Hostel/UpdateHostel';
import UpdateRoom from '../components/Room/UpdateRoom';
import RoomListForAdmin from '../components/Room/RoomListForAdmin';
import EmployeeUpdate from '../components/Employee/EmployeeUpdate';
import PaymentList from '../components/Payment/PaymentList';
import Logout from '../components/Home/Logout';


class EmployeeRouter extends Component {
    render() {

            return (
                <div>
                    <Router>
                        <Routes>
                            <Route exact path="employee" element={<SharedEmployeeLayout />}>
                                <Route index element={<EmployeeDashboard />} />
                                <Route path="student/display" element={<StudentsList />} />
                                <Route path="hostel/display/add" element={<AddHostel />} />
                                <Route path='hostel/room/add' element={<AddRooms />} />
                                <Route path='room/update' element={<UpdateRoom />} />
                                <Route path="concern/display" element={<ConcernsList />} />
                                <Route path="profile" element={<EmployeeProfile />} />
                                <Route path="hostel/display" element={<HostelsList />} />
                                <Route path="hostel/room" element={<RoomListForAdmin />} />
                                <Route path="hostel/update" element={<UpdateHostel />} />
                                <Route path="update" element={<EmployeeUpdate />} />
                                <Route path="payments" element={<PaymentList />} />
                                <Route path="logout" element={<Logout />} />
                            </Route>
                        </Routes>
                    </Router>

                </div>
            );        
    }
}

export default EmployeeRouter;