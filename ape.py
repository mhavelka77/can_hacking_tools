import isotp
import can

def main():
    # Create a CAN bus instance (assuming you are using a virtual CAN bus, 'vcan0')
    bus = can.interface.Bus(channel='vcan0', bustype='socketcan')

    # Create an ISOTP socket
    addr = isotp.Address(isotp.AddressingMode.Normal_11bits, txid=0x7DF, rxid=0x7E8)
    stack = isotp.CanStack(bus, address=addr)

    # Create UDS message (example: Diagnostic Session Control - Default Session)
    uds_request = bytearray([0x10, 0x01])  # SID 0x10, Sub-function 0x01

    # Send UDS request
    stack.send(uds_request)

    # Wait and receive the response
    response = stack.recv()

    if response:
        print("Received response:", response.hex())
    else:
        print("No response received.")

if __name__ == '__main__':
    main()
