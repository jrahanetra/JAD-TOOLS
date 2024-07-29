import React, { useEffect, useRef, useState } from "react";
import { SelectChangeEvent } from "@mui/material";
import TableData from "../components/Attendance/Table/AttendTable";
import FilterComponent from "../components/Attendance/Filtering/filter";
import InputSearch from "../components/common/inputSearch";
import SelectFilterComponent from "../components/common/selectInputForFilter";

type FormValuesFilter = {
    byDate: string;
    byLevel: string;
    byName: string;
    byAttend: string;
}

type ValidationFormValuesFilters = {
    byDate: boolean;
    byLevel: boolean;
    byName: boolean;
    byAttend: boolean;
}
function Attend() {
    const [valueSearchInput, setValueSearchInput] = useState<string>("")

    const [valuesFilter, setValuesFilter] = useState<FormValuesFilter>({
        byDate: '',
        byLevel: '',
        byName: '',
        byAttend: '',
    })

    const handleChangeValue = (forSelectInput: string) => (event: SelectChangeEvent<string>) => {
        setValuesFilter({
            ...valuesFilter,
            [forSelectInput]: event?.target.value
        })
    }
    const handleChangeValueSearchInput = () => (event: React.ChangeEvent<HTMLInputElement>) => {
        console.log(event.target.value)
        setValueSearchInput(event?.target.value)
    }
    const [validValuesFilter, setValidValuesFilter] = useState<ValidationFormValuesFilters>({
        byDate: false,
        byLevel: false,
        byName: false,
        byAttend: false
    })

    const selectFilterDate = useRef<HTMLDivElement>(null);
    const selectFilterLevel = useRef<HTMLDivElement>(null);
    const selectFilterName = useRef<HTMLDivElement>(null);
    const selectFilterAttend = useRef<HTMLDivElement>(null);

    useEffect(() => {
        const result = (valuesFilter.byDate !== '')
        setValidValuesFilter({
            ...validValuesFilter,
            byDate: result
        })
    }, [valuesFilter.byDate])
    useEffect(() => {
        const result = (valuesFilter.byLevel !== '')
        setValidValuesFilter({
            ...validValuesFilter,
            byLevel: result
        })
    }, [valuesFilter.byDate])
    useEffect(() => {
        const result = (valuesFilter.byName !== '')
        setValidValuesFilter({
            ...validValuesFilter,
            byName: result
        })
    }, [valuesFilter.byDate])
    useEffect(() => {
        const result = (valuesFilter.byAttend !== '')
        setValidValuesFilter({
            ...validValuesFilter,
            byAttend: result
        })
    }, [valuesFilter.byDate])

    return (
        <div className="container-attend">
            <div className="container-title">
                <div className="container-filter">
                    <SelectFilterComponent
                        nameLabel="↓↑Date"
                        widthSelect={90}
                        valuesPossible={['↓Recent', '↑Once']} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
                        value={valuesFilter.byDate}
                        handleChange={handleChangeValue("byDate")}
                        onKeyPress={() => { }}
                        inputRef={selectFilterDate}
                        isValid={validValuesFilter.byDate}
                        isWithLabel={false}
                    />
                    <SelectFilterComponent
                        nameLabel="↓↑Level"
                        widthSelect={90}
                        valuesPossible={['L1', 'L2']} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
                        value={valuesFilter.byLevel}
                        handleChange={handleChangeValue("byLevel")}
                        onKeyPress={() => { }}
                        inputRef={selectFilterLevel}
                        isValid={validValuesFilter.byLevel}
                        isWithLabel={false}
                    />
                    <SelectFilterComponent
                        nameLabel="↓↑Name"
                        widthSelect={90}
                        valuesPossible={['↓A', '↑Z']} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
                        value={valuesFilter.byName}
                        handleChange={handleChangeValue("byName")}
                        onKeyPress={() => { }}
                        inputRef={selectFilterName}
                        isValid={validValuesFilter.byName}
                        isWithLabel={false}
                    />
                    <SelectFilterComponent
                        nameLabel="Attend"
                        widthSelect={90}
                        valuesPossible={['YES', 'NO']} // LEVEL POSSIBLE THAT WE'VE TO VERIFY IN THE BASE
                        value={valuesFilter.byAttend}
                        handleChange={handleChangeValue("byAttend")}
                        onKeyPress={() => { }}
                        inputRef={selectFilterAttend}
                        isValid={validValuesFilter.byAttend}
                        isWithLabel={false}
                    />
                </div>
                <InputSearch
                    searchValue={valueSearchInput}
                    handleChange={handleChangeValueSearchInput()}
                />
            </div>
            <div className="container-list">
                <div className="div-refresh">
                    <a href="/" className="a-refresh">Refresh</a>
                </div>
                <div className="div-container-tabFilter">
                    <TableData />
                </div>
            </div>
        </div>
    )
}
export default Attend;