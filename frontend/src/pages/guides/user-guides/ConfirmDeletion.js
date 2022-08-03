import './confirm-deletion.css';

const ConfirmDeletion = ({display, setDisplay, confirmDelete}) => {
  return (
    <>
    {
        (display)?
        <div className='confdel-bg'>
            <div className="confdel-container">
                <p className="confdel-yousure">Are you sure you want to delete this guide?</p>
                <div className="confdel-delbtns-container">
                    <button className="confdel-cancel-btn confdel-btn" onClick={() => {
                        setDisplay(false);
                    }}>Cancel</button>
                    <button className="confdel-cont-btn confdel-btn" onClick={() => {
                        confirmDelete();
                    }}>Continue</button>
                </div>
            </div>
        </div>
        :
        null
    }
    </>
  );
};

export default ConfirmDeletion;
