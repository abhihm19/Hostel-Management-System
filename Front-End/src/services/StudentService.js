import axios from 'axios';

const BASE_URL = "http://localHost:7777/student";

class StudentService {

    addStudent(studentDto) {
        return axios.put(BASE_URL, studentDto);
    }

    updateStudent(studentDto) {
        return axios.put(BASE_URL, studentDto);
    } 
    
    getStudent() {
        return axios.get(BASE_URL);
    }

    getAllStudents() {
        return axios.get(BASE_URL + "/get-all-students");
    }

    getAllottmentStatus() {
        return axios.get(BASE_URL + "/allottment");
    }
}

export default new StudentService();