import React, { Component } from 'react';
import { Outlet } from 'react-router-dom';
import SideEmployeeNavBar from '../Navbar/EmployeeNavBar';

class SharedEmployeeLayout extends Component {
    render() {
        return (
            <div className='flex'>
                <SideEmployeeNavBar />                
                <Outlet />                 
            </div>
        );
    }
}

export default SharedEmployeeLayout;