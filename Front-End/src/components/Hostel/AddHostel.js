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
          <h5 className="card-header text-center" style={{ backgroundColor: "rgb(43, 43, 43)", color: "white"}}>Add Hostel</h5>
          <form
            className="card-body"
            style={{ padding: "20px 50px 20px 50px" }}
            onSubmit={handlesubmit}
          >
            <div className="row">
              <div className="col-lg-4">
                <h6>Name</h6>
              </div>
              <div className="col-lg-8">
                <div className="mb-4">
                  <input className="form-control" type="text" name="name" />
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-4">
                <h6>Contact Person</h6>
              </div>
              <div className="col-lg-8">
                <div className="mb-4">
                  <input
                    className="form-control"
                    type="text"
                    name="contactPerson"
                  />
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-lg-4">
                <h6>Hostel Fees</h6>
              </div>
              <div className="col-lg-8 mb-4">
                <input
                  className="form-control"
                  type="text"
                  id="fees"
                  name="hostelFees"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-lg-4">
                <h6>Contact Mobile No.</h6>
              </div>
              <div className="col-lg-8 mb-4">
                <input
                  className="form-control"
                  type="text"
                  id="contactMobileNo"
                  name="contactMobileNo"
                />
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
                    rows="3"
                    required
                    title="Please enter your address"
                  />
                </div>
              </div>
            </div>
            <div className="d-grid gap-2 col-6 mx-auto">
              <button className="btn btn-primary w-100 mb-3" type="submit">Submit</button>
              <button className="btn btn-danger w-100 mb-3" onClick={(e) => cancel()}>Cancel</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
