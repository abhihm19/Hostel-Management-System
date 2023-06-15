import React from "react";
import { useNavigate } from "react-router-dom";
import HostelService from "../../services/HostelService";

export default function AddHostel() {

  const navigate = useNavigate();
  const handlesubmit = (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);
    const hostelDto = Object.fromEntries(formData);

    HostelService.addHostel(hostelDto)
      .then((res) => {
        navigate("/employee/hostel/display");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const cancel = () => {
    navigate("/employee/hostel/display");
  }

  return (
    <div className="row w-50 my-4 mx-5">
      <div className="col-lg-12 col-xl-12">
        <div className="card">
          <h5 className="card-header text-center" style={{ backgroundColor: "rgb(43, 43, 43)", color: "white" }}>Add Hostel</h5>
          <form
            className="card-body"
            style={{ padding: "20px 50px 20px 50px" }}
            onSubmit={handlesubmit}
          >
            <div className="row">              
              <div className="col mb-4">
                <input
                  className="form-control"
                  type="text"
                  name="name"
                  id="name"
                  placeholder="Hostel Name"
                />
              </div>
            </div>
            <div className="row">
              <div className="col mb-4">
                <input
                  className="form-control"
                  type="text"
                  id="fees"
                  name="hostelFees"
                  placeholder="Hostel Fees"
                />
              </div>
            </div>
            <div className="row">
              <div className="col mb-4">
                <input
                  className="form-control"
                  type="text"
                  id="contactPerson"
                  name="contactPerson"
                  placeholder="Contact Person"
                />
              </div>
            </div>
            <div className="row">
              <div className="col mb-4">
                <input
                  className="form-control"
                  type="text"
                  id="contactNumber"
                  name="contactNumber"
                  placeholder="Contact Number"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-lg-8 mb-4">
                <textarea
                  className="form-control"
                  type="text"
                  name="address"
                  rows="3"
                  title="Please enter your address"
                  placeholder="Hostel Address"
                />
              </div>
            </div>
            <button className="btn btn-primary w-100 mb-3" type="submit">Submit</button>
            <button className="btn btn-danger w-100 mb-3" onClick={(e) => cancel()}>Cancel</button>
          </form>
        </div>
      </div >
    </div >
  );
}
