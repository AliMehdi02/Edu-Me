import {useState} from 'react';

const Topic = ({expLvl, id, idToTopic}) => {
    const [selectedRadioBtn, setSelectedRadioBtn] = useState('');

    const isRadioSelected = value => selectedRadioBtn === value;

    const handleRadioCheck = (e) => {
        setSelectedRadioBtn(e.currentTarget.value);
        expLvl(e.target.value, id);
    }

    return (
        <div className="topic-division">
            <p className="sub-name">{idToTopic(id)}</p>

            <div>
                <input type='radio' name={id} value='novice' checked={isRadioSelected('novice')} onChange={handleRadioCheck} className='radio-btn rb1' />
                <input type='radio' name={id} value='beginner' checked={isRadioSelected('beginner')} onChange={handleRadioCheck} className='radio-btn rb2' />
                <input type='radio' name={id} value='intermediate' checked={isRadioSelected('intermediate')} onChange={handleRadioCheck} className='radio-btn rb3' />
                <input type='radio' name={id} value='advanced' checked={isRadioSelected('advanced')} onChange={handleRadioCheck} className='radio-btn rb4' />
            </div>

            <p className="exp-text">Novice &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            ......
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Advanced</p>
        </div>
    )
}

export default Topic
