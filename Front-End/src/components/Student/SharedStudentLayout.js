import React, { Component } from 'react';
import { Outlet } from 'react-router-dom';
import StudentNavBar from '../Navbar/StudentNavBar';
import Footer from '../Footer';

class SharedStudentLayout extends Component {
    render() {
        return (
            <div className='flex'>
                <StudentNavBar />  
                <Footer />              
                <Outlet /> 
            </div>
        );
    }
}

export default SharedStudentLayout;