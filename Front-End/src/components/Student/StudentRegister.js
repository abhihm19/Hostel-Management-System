import React, { useEffect, useState } from "react";
import StudentService from "../../services/StudentService";
import AuthService from "../../services/AuthService";

export default function StudentRegister() {

  const [user, setuser] = useState({})

  const handlesubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const studentDto = Object.fromEntries(formData);

    StudentService.addStudent(studentDto)
      .then(response => {
        console.log("student details added :" + response)
        alert("student details added")

      }).catch((err) => {
        console.log(err)
      })

  }

  useEffect(() => {
    AuthService.getuser().then((response) => {
      console.log(response)
      setuser(response.data)
    }).catch((err) => {
      console.log(err)
    })
  }
    , [])

  let authenticated = sessionStorage.getItem("authenticated")
  // let navigate = useNavigate()
  if (authenticated) {
    return (
      <div className="row w-50 my-4 mx-5">
        <div className="col-lg-12 col-xl-12">
          <div className="card">
            <h5 className="card-header text-center">
              Student Registration Form
            </h5>
            <form
              className="card-body"
              style={{ padding: "20px 50px 20px 50px" }}
              onSubmit={handlesubmit}
            >
              <div className="row">
                <div className="col">
                  <h6>Name</h6>
                </div>
                <div className="col">
                  <div className="mb-4">
                    <input
                      className="form-control"
                      type="text"
                      name="firstName"
                      defaultValue={user.firstName}
                      disabled
                    />
                  </div>
                </div>
                <div className="col">
                  <div className="mb-4">
                    <input
                      className="form-control"
                      type="text"
                      name="lastName"
                      defaultValue={user.lastName}
                      disabled
                    />
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>User Name</h6>
                </div>
                <div className="col-lg-8">
                  <div className="mb-4">
                    <input
                      className="form-control"
                      type="text"
                      name="userName"
                      defaultValue={user.userName}
                      disabled
                    />
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Email</h6>
                </div>
                <div className="col-lg-8">
                  <div className="mb-4">
                    <input
                      className="form-control"
                      type="text"
                      name="emailId"
                    />
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Mobile number</h6>
                </div>
                <div className="col-lg-8">
                  <div className="mb-4">
                    <input
                      className="form-control"
                      type="text"
                      name="mobileNo"
                      required
                      pattern="[789][0-9]{9}"
                      title="Please enter valid mobile number"
                    />
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Academic Year</h6>
                </div>
                <div className="col-lg-8">
                  <select className="form-select mb-4" name="course">
                    <option selected>Select Academic Year</option>
                    <option defaultValue="2023-24">2023-24</option>
                    <option defaultValue="2022-23">2022-23</option>
                    <option defaultValue="2021-22">2021-22</option>
                  </select>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Degree</h6>
                </div>
                <div className="col-lg-8">
                  <select className="form-select mb-4" name="course">
                    <option selected>Select Degree</option>
                    <option defaultValue="BE">B.E</option>
                    <option defaultValue="BSC">B.Sc</option>
                    <option defaultValue="MSC">M.Sc</option>
                    <option defaultValue="ME">M.E</option>
                    <option defaultValue="PGD">PG-Diploma</option>
                  </select>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Course</h6>
                </div>
                <div className="col-lg-8">
                  <select className="form-select mb-4" name="course">
                    <option selected>Select Course</option>
                    <option defaultValue="DAC">DAC</option>
                    <option defaultValue="DBDA">DBDA</option>
                    <option defaultValue="DESD">DESD</option>
                    <option defaultValue="DITISS">DITISS</option>
                    <option defaultValue="DIoT">DIoT</option>
                  </select>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Date of birth</h6>
                </div>
                <div className="col-lg-8 mb-4">
                  <input
                    className="form-control"
                    type="date"
                    id="birthday"
                    name="dateOfBirth"
                  />
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Gender</h6>
                </div>
                <div className="col-lg-8">
                  <select className="form-select mb-4" name="gender">
                    <option selected>Select Gender</option>
                    <option defaultValue="1">Male</option>
                    <option defaultValue="2">Female</option>
                  </select>
                </div>
              </div>

              <div className="row">
                <div className="col-lg-4">
                  <h6>Address</h6>
                </div>
                <div className="col-lg-8">
                  <div className="mb-4">
                    <textarea
                      className="form-control"
                      type="text"
                      name="address"
                      rows="4"
                      required
                      title="Please enter your address"
                    />
                  </div>
                </div>
              </div>

              <div className="d-grid gap-2 col-6 mx-auto">
                <button className="btn btn-primary w-100 mb-3" type="submit">
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    );
  }
  else {
    return (<div>404 NOT FOUND</div>);
  }

}

